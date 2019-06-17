package avans.ivh11.proftaak.stubs;


import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Service.DishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class DishServiceTest {

    private static final String DISH_NAME = "Kaas";
    private static final String DISH_TYPE = "Voorgerecht";

    @Autowired
    private DishService dishService;

    @Test
    public void create(){
        //execute
        Dish dish = createDish(DISH_NAME, DISH_TYPE);

        //verify
        List<Dish> dishes = dishService.findAll();
        assertNotNull(dishes);
        assertTrue("created a dish" ,dishes.contains(dish));
    }



    private Dish createDish(String dishName, String dishType) {
        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setDishType(dishType);
        Dish retval = dishService.save(dish);
//        assertNotNull(retval);
//        assertNotNull(retval.getId());
//        assertEquals("dishName", dishName, retval.getDishName());
//        assertEquals("dishType", dishType, retval.getDishType());
        return retval;
    }
}
