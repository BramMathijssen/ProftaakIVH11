package avans.ivh11.proftaak.Repository;

import avans.ivh11.proftaak.Domain.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {

    @Query("SELECT d FROM Dish d WHERE LOWER(d.dishType) = LOWER(:dishType)")
    public List<Dish> findDishesByType(@Param("dishType") String dishType);



}