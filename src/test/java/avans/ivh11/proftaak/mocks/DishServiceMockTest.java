package avans.ivh11.proftaak.mocks;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Repository.DishRepository;
import avans.ivh11.proftaak.Service.impl.DishServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DishServiceMockTest {

    private static final String DISH_NAME = "Kaas";
    private static final String DISH_TYPE = "Voorgerecht";

    //Mock the service which is to be tested (Can't be a interface)
    @InjectMocks
    DishServiceImpl dishService;

    //Mock the service dependencies(=DishServiceImpl is dependent on dishRepo)
    @Mock
    DishRepository dishRepository;

    //https://hellokoding.com/spring-boot-test-service-layer-example-with-mockitos-mock-and-injectmock/
    @Test
    public void create(){

        //Arange
        Dish dish = createDish(DISH_NAME, DISH_TYPE);

        List<Dish> expectedDishes = Arrays.asList(dish);

        //Act
        doReturn(expectedDishes).when(dishRepository).findAll(); //Argument passed to when has to be a Mock so dishRepository instead of dishService

        List<Dish> actualDishes = dishService.findAll();

        //Assert
        assertThat(actualDishes).isEqualTo(expectedDishes);
    }


    private Dish createDish(String dishName , String dishType) {
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
