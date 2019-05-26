package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.Meal;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.MealRepository;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private MealService mealService;

    private final MealRepository mealRepository;
    private final StudentRepository studentRepository;

    public MealController(MealRepository mealRepository, StudentRepository studentRepository){
        this.mealRepository = mealRepository;
        this.studentRepository = studentRepository;
    }


    @GetMapping
    public ModelAndView list(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(2);

        Page<Meal> mealPage = mealService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("mealPage", mealPage);

        int totalPages = mealPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        //Iterable<Meal> meals = this.mealRepository.findAll();
        return new ModelAndView("meals/list" , "mealPage", mealPage);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Meal meal) {
        return new ModelAndView("meals/view", "meal", meal);
    }

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute Meal meal , Model model) {
        Iterable<Student> students = this.studentRepository.findAll();
        model.addAttribute("students" , students);
        return "meals/form";
    }

    @PostMapping
    public ModelAndView create(@Valid Meal meal, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("meals/form", "formErrors", result.getAllErrors());
        }
        meal = this.mealRepository.save(meal);

        redirect.addFlashAttribute("globalMessage", "meals.view.success");
        return new ModelAndView("redirect:/m/{meal.id}", "meal.id", meal.getId());
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







