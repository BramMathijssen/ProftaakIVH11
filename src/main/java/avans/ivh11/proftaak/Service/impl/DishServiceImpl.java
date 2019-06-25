package avans.ivh11.proftaak.Service.impl;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Repository.DishRepository;
import avans.ivh11.proftaak.Service.DishService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Transactional(readOnly = true)
    public List<Dish> findAll() {
        // MySQL and H2 return the restaurants of findAll() in different order
        // sorting the result makes the behavior less database vendor dependent
        return Lists.newArrayList(dishRepository.findAll());
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Transactional(readOnly = true)
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    public void deleteById(Long id) {
        dishRepository.deleteById(id);
    }

}