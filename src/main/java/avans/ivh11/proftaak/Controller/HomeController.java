package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.Meal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

    @GetMapping
    //@RequestMapping(value = "/locale", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("home/index");
    }

//    @RequestMapping(value = "/locale", method = RequestMethod.GET)
//    public String getLocalePage() {
//        return "my-locale";
//    }
}
