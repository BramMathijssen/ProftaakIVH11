package avans.ivh11.proftaak.Controller.API;


import avans.ivh11.proftaak.Controller.DishController;
import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dishes")

public class DishAPIController {

    private final Logger logger = LoggerFactory.getLogger(DishController.class);

    @Autowired
    private DishService dishService;


    @GetMapping
    public ResponseEntity<List<Dish>> findAll() {
        return ResponseEntity.ok(dishService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> findById(@PathVariable Long id) {
        Optional<Dish> dish = dishService.findById(id);
        if (!dish.isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dish.get());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Dish dish) {
        return ResponseEntity.ok(dishService.save(dish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!dishService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        dishService.deleteById(id);
        logger.debug("Deleted Dish with id " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> update(@PathVariable Long id, @Valid @RequestBody Dish dish) {
        if (!dishService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dishService.save(dish));
    }


}