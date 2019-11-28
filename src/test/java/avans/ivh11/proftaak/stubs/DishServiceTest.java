package avans.ivh11.proftaak.stubs;


import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Service.DishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class DishServiceTest {

    private static final String DISH_NAME = "Kaas";
    private static final String DISH_TYPE = "Voorgerecht";

    private static final String DISH_NAME2 = "Spaghetti";
    private static final String DISH_TYPE2 = "Hoofdgerecht";

    @Autowired
    private DishService dishService;

    @Test
    public void create(){
        //execute
        Dish dish = createDish2(DISH_NAME, DISH_TYPE);

        //verify
        List<Dish> dishes = dishService.findAll();
        assertNotNull(dishes);
        assertTrue("created a dish" ,dishes.contains(dish));
    }


    @Test
    public void findById(){
        //execute
        Dish dish = createDish2(DISH_NAME, DISH_TYPE);

        Long dishId = dish.getId();

        Optional<Dish> dish2 = dishService.findById(dishId);
        //verify
        assertNotNull(dish2); //Not necessary because a Optional is not Null
        //assertEquals(Optional.of(dish2), dish);
        assertEquals("created a dish", dish2.get(), dish);
    }

    @Test
    public void findBy_invalidId(){
        //execute
        Dish dish = createDish2(DISH_NAME, DISH_TYPE);

        Long dishId = dish.getId()+1L;

        Optional<Dish> dish2 = dishService.findById(dishId);

        //verify
        if(dish2.isPresent()){
            assertFalse("Dish found", true);
        }else{
            assertTrue("Dish not found", true);
        }
    }

    @Test
    public void delete() {
        // prepare
        Dish dish = createDish2(DISH_NAME, DISH_TYPE);
        List<Dish> dishes = dishService.findAll();
        assertNotNull(dishes);
        assertTrue("created dish in list", dishes.contains(dish));

        // execute
        dishService.deleteById(dish.getId());

        // verify
        List<Dish> dish2 = dishService.findAll();
        assertNotNull(dish2);
        assertFalse("deleted dish not in the list", dish2.contains(dish));
    }


    @Test
    public void update() {
        Dish dish = createDish2(DISH_NAME, DISH_TYPE);
        dish.setDishName(DISH_NAME2);
        dish.setDishType(DISH_TYPE2);

        dishService.save(dish);

        Optional<Dish> dish2 = dishService.findById(dish.getId());

        assertEquals("dishName", DISH_NAME2, dish2.get().getDishName());
        assertEquals("dishType", DISH_TYPE2, dish2.get().getDishType());
    }

    @Test
    public void update_invalid_dish() {
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setDishName("Kaas");
        dish.setDishType("Voorgerecht");

        dishService.save(dish);

        Optional<Dish> dish2 = dishService.findById(dish.getId()+1L);

        if(dish2.isPresent()){
            dish2.get().setDishName("Carpaccio");
            dish2.get().setDishType("Nagerecht");
            assertFalse("Dishname", DISH_NAME.equals(dish2.get().getDishName()));
            assertFalse("Dishname", DISH_TYPE.equals(dish2.get().getDishType()));
        }
        else{
            assertFalse("Dish was not found, so not updated" , dish2.isPresent() );
        }
    }


    private Dish createDish2(String dishName, String dishType) {
        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setDishType(dishType);
        Dish retval = dishService.save(dish);

        return retval;
    }
}
