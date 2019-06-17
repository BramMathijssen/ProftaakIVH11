package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Repository.DishRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/d")
public class DishController {
    //Jenkins
    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public ModelAndView list() {
        Iterable<Dish> dishes = this.dishRepository.findAll();
        return new ModelAndView("dishes/list", "dishes", dishes);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Dish dish) {
        return new ModelAndView("dishes/view", "dish", dish);
    }

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(@ModelAttribute Dish dish) {
        return "dishes/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@Valid Dish dish, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("dishes/form", "formErrors", result.getAllErrors());
        }
        dish = this.dishRepository.save(dish);
        redirect.addFlashAttribute("globalMessage", "view.success");
        return new ModelAndView("redirect:/d/{dish.id}", "dish.id", dish.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.dishRepository.deleteById(id);
        Iterable<Dish> dishes = this.dishRepository.findAll();
        return new ModelAndView("dishes/list", "dishes", dishes);
    }

    @GetMapping("/modify/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView modifyForm(@PathVariable("id") Dish dish) {
        return new ModelAndView("dishes/form", "dish", dish);
    }
}