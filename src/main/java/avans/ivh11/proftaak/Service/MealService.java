package avans.ivh11.proftaak.Service;

import avans.ivh11.proftaak.Domain.Meal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface MealService {

    List<Meal> findAll();

    Meal save(Meal meal);

    Optional<Meal> findById(Long id);

    void deleteById(Long id);

    ArrayList<Meal> getMealsList();

    Page<Meal> findPaginated(Pageable pageable);



}
