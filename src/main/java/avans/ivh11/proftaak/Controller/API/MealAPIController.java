package avans.ivh11.proftaak.Controller.API;

import avans.ivh11.proftaak.Controller.MealController;
import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/meals")

public class MealAPIController {

    private final Logger logger = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealService mealService;


    @GetMapping
    public ResponseEntity<List<Meal>> findAll() {
        return ResponseEntity.ok(mealService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> findById(@PathVariable Long id) {
        Optional<Meal> meal = mealService.findById(id);
        if (!meal.isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(meal.get());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Meal meal) {
        return ResponseEntity.ok(mealService.save(meal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!mealService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        mealService.deleteById(id);
        logger.debug("Deleted Meal with id " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meal> update(@PathVariable Long id, @Valid @RequestBody Meal meal) {
        if (!mealService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(mealService.save(meal));
    }
}