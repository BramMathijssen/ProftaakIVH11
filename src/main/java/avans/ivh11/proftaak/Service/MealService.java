package avans.ivh11.proftaak.Service;

import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Repository.MealRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MealService {

    @Autowired
     private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository){
        this.mealRepository = mealRepository;

    }

    public ArrayList<Meal> getMealsList() {
        return (ArrayList<Meal>) this.mealRepository.findAll();
    }


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



}
