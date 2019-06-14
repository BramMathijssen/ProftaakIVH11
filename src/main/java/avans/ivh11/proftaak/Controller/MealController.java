package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.AOP.ExecutionTime;
import avans.ivh11.proftaak.AOP.LoggerSingleton;
import avans.ivh11.proftaak.Domain.BaseStudent;
import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.BaseStudentRepository;
import avans.ivh11.proftaak.Repository.DishRepository;
import avans.ivh11.proftaak.Repository.MealRepository;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/m")
public class MealController {

    private final Logger logger = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealService mealService;

    private final MealRepository mealRepository;
    private final StudentRepository studentRepository;
    private final DishRepository dishRepository;

    public MealController(MealRepository mealRepository, StudentRepository studentRepository, DishRepository dishRepository){
        this.mealRepository = mealRepository;
        this.studentRepository = studentRepository;
        this.dishRepository = dishRepository;
    }


    @GetMapping
    @ExecutionTime
    public ModelAndView list(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){



        //logger.debug("Meals list called" );

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Meal> mealPage = mealService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("mealPage", mealPage);

        int totalPages = mealPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return new ModelAndView("meals/list" , "mealPage", mealPage);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Meal meal) {

        logger.debug("View of meal called. - id:  " + meal.getId());

        return new ModelAndView("meals/view", "meal", meal);
    }

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(@ModelAttribute Meal meal , Model model) {
        Iterable<Student> students = this.studentRepository.findAll();
        Iterable<Dish> dishes = this.dishRepository.findAll();
        Iterable<Dish> dishesStarters = this.dishRepository.findDishesByType("Voorgerecht");
        Iterable<Dish> dishesMaincourses = this.dishRepository.findDishesByType("Hoofdgerecht");
        Iterable<Dish> dishesDeserts = this.dishRepository.findDishesByType("Nagerecht");
        model.addAttribute("students" , students);
        model.addAttribute("dishes" , dishes);
        model.addAttribute("dishesStarters" , dishesStarters);
        model.addAttribute("dishesMaincourses" , dishesMaincourses);
        model.addAttribute("dishesDeserts" , dishesDeserts);
        return "meals/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@Valid Meal meal,  BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            logger.debug("Form not created, result has errors." );
            return new ModelAndView("meals/form", "formErrors", result.getAllErrors());
        }
        meal = this.mealRepository.save(meal);

        logger.debug("Meal Created and saved. - id:  " + meal.getId());

        redirect.addFlashAttribute("globalMessage", "meals.view.success");
        return new ModelAndView("redirect:/m/{meal.id}", "meal.id", meal.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.mealRepository.deleteById(id);
        Iterable<Meal> meals = this.mealRepository.findAll();
        return new ModelAndView("meals/list", "meals", meals);
    }

    @GetMapping("modify/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView modifyForm(@PathVariable("id") Meal meal) {
        return new ModelAndView("meals/form", "meal", meal);
    }


}







