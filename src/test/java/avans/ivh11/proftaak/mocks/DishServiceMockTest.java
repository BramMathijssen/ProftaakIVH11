package avans.ivh11.proftaak.mocks;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Repository.DishRepository;
import avans.ivh11.proftaak.Service.impl.DishServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doReturn;


@RunWith(MockitoJUnitRunner.class)
public class DishServiceMockTest {

    private static final String DISH_NAME = "Kaas";
    private static final String DISH_TYPE = "Voorgerecht";
    private static final Long DISH_ID = 23L;

    private static final String DISH_NAME2 = "Pizza";
    private static final String DISH_TYPE2 = "Nagerecht";
    private static final Long DISH_ID2 = 24L;

    //Mock the service dependencies(=DishServiceImpl is dependent on dishRepo)
    @Mock
    DishRepository dishRepository;

    //Mock the service which is to be tested (Can't be a interface)
    @InjectMocks
    DishServiceImpl dishService;


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
        //Arrange
        Dish dish = createDish(DISH_ID, DISH_NAME, DISH_TYPE);

        //Act
        Mockito.when(dishRepository.findById(DISH_ID)).thenReturn(Optional.of(dish));

        //Assert
        assertThat(dishService.findById(DISH_ID)).isEqualTo(Optional.of(dish));

    }

    @Test
    public void findAllDishes(){
        //Arrange
        Dish dish1 = createDish(DISH_ID , DISH_NAME , DISH_TYPE);
        Dish dish2 = createDish(DISH_ID2, DISH_NAME2, DISH_TYPE2);

        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish1);
        dishList.add(dish2);

        //Act
        Mockito.when(dishRepository.findAll()).thenReturn(dishList);

        //Assert
        assertThat(dishService.findAll()).isEqualTo(dishList);
    }

    @Test
    public void deleteDish(){
        //Arrange
        Dish dish = createDish(DISH_ID, DISH_NAME, DISH_TYPE);

        //Act
        Mockito.when(dishRepository.findById(DISH_ID)).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.existsById(dish.getId())).thenReturn(false);

        //Assert
        assertFalse(dishRepository.existsById(dish.getId()));

    }


    private Dish createDish(Long id, String dishName , String dishType) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setDishName(dishName);
        dish.setDishType(dishType);

        return dish;
    }
}
