package avans.ivh11.proftaak.mocks;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Repository.DishRepository;
import avans.ivh11.proftaak.Service.impl.DishServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DishServiceMockTest {

    private static final String DISH_NAME = "Kaas";
    private static final String DISH_TYPE = "Voorgerecht";
    private static final Long DISH_ID = 23L;

    private static final String DISH_NAME2 = "Pizza";
    private static final String DISH_TYPE2 = "Nagerecht";
    private static final Long DISH_ID2 = 24L;

    //Mock the service which is to be tested (Can't be a interface)
    @InjectMocks
    DishServiceImpl dishService;

    //Mock the service dependencies(=DishServiceImpl is dependent on dishRepo)
    @Mock
    DishRepository dishRepository;

    @Test
    public void testSaveDish(){
        Dish dish = new Dish();
        dish.setDishName(DISH_NAME);
        dish.setDishType(DISH_TYPE);

        List<Dish> dish2 = dishService.findAll();

        Mockito.when(dishRepository.save(dish)).thenReturn(dish);

        assertThat(dish2).isEqualTo(dish);
    }


    //https://hellokoding.com/spring-boot-test-service-layer-example-with-mockitos-mock-and-injectmock/
    @Test
    public void create(){

        //Arange
        Dish dish = createDish(DISH_ID, DISH_NAME, DISH_TYPE);

        List<Dish> expectedDishes = Arrays.asList(dish);

        //Act
        doReturn(expectedDishes).when(dishRepository).findAll(); //Argument passed to when has to be a Mock so dishRepository instead of dishService

        List<Dish> actualDishes = dishService.findAll();

        //Assert
        assertThat(actualDishes).isEqualTo(expectedDishes);
    }


    @Test
    public void findById(){
        //Arange
        Dish dish = createDish(DISH_ID, DISH_NAME, DISH_TYPE);

        Mockito.when(dishRepository.findById(DISH_ID)).thenReturn(Optional.of(dish));
        assertThat(dishService.findById(DISH_ID)).isEqualTo(dish);

        //Optional<Dish> dishes = dishService.findById(DISH_ID);

        //Optional<Dish> dishes = dishService.findById(dishId);

        //when(dishRepository.findById(dishId)).thenReturn(Optional.of(dish));

        //assertThat(dish).isEqualTo(dishes);
    }

    @Test
    public void findAllDishes(){
        Dish dish1 = createDish(DISH_ID , DISH_NAME , DISH_TYPE);
        Dish dish2 = createDish(DISH_ID2, DISH_NAME2, DISH_TYPE2);

        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish1);
        dishList.add(dish2);

        Mockito.when(dishRepository.findAll()).thenReturn(dishList);
        assertThat(dishService.findAll()).isEqualTo(dishList);
    }

    @Test
    public void deleteDish(){
        Dish dish = createDish(DISH_ID, DISH_NAME, DISH_TYPE);

        Mockito.when(dishRepository.findById(DISH_ID)).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.existsById(dish.getId())).thenReturn(false);
        assertFalse(dishRepository.existsById(dish.getId()));

    }


    private Dish createDish(Long id, String dishName , String dishType) {
        Dish dish = new Dish();
        dish.setId(id);
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