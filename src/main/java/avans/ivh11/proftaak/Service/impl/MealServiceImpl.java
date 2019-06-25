package avans.ivh11.proftaak.Service.impl;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Repository.MealRepository;
import avans.ivh11.proftaak.Service.MealService;
import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Transactional(readOnly = true)
    public ArrayList<Meal> getMealsList() {
        return (ArrayList<Meal>) this.mealRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Meal> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Meal> list;

        if (getMealsList().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, getMealsList().size());
            list = getMealsList().subList(startItem, toIndex);
        }

        Page<Meal> mealPage
                = new PageImpl<Meal>(list, PageRequest.of(currentPage, pageSize), getMealsList().size());

        return mealPage;
    }

    @Transactional(readOnly = true)
    public List<Meal> findAll() {
        // MySQL and H2 return the restaurants of findAll() in different order
        // sorting the result makes the behavior less database vendor dependent
        return Lists.newArrayList(mealRepository.findAll());
    }

    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    //@Transactional(readOnly = true)
    public Optional<Meal> findById(Long id) {
        return mealRepository.findById(id);
    }

    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }
}
