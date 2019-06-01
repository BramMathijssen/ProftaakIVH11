package avans.ivh11.proftaak.Repository;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Domain.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Long> {


}
