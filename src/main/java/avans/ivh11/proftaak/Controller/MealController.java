package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Repository.MealRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
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

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Meal meal) {
        return new ModelAndView("meals/view", "meal", meal);
    }

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute Meal meal) {
        return "meals/form";
    }

    @PostMapping
    public ModelAndView create(@Valid Meal meal, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("meals/form", "formErrors", result.getAllErrors());
        }
        meal = this.mealRepository.save(meal);
        redirect.addFlashAttribute("globalMessage", "view.success");
        return new ModelAndView("redirect:/{meal.id}", "meal.id", meal.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.mealRepository.deleteById(id);
        Iterable<Meal> meals = this.mealRepository.findAll();
        return new ModelAndView("meals/list", "meals", meals);
    }

    @GetMapping("modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Meal meal) {
        return new ModelAndView("meals/form", "meal", meal);
    }


}







