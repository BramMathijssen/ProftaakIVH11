package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Repository.MealRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Meals")
public class MealController {

    private final MealRepository mealRepository;

    public MealController(MealRepository mealRepository){
        this.mealRepository = mealRepository;
    }

    @GetMapping
    public ModelAndView list(){
        Iterable<Meal> meals = this.mealRepository.findAll();

        return new ModelAndView("meals/list" , "meals", meals);
    }


}
