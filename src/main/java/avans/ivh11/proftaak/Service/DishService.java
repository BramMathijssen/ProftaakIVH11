package avans.ivh11.proftaak.Service;

import avans.ivh11.proftaak.Domain.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<Dish> findAll();

    Dish save(Dish dish);

    Optional<Dish> findById(Long id);

    void deleteById(Long id);

}
