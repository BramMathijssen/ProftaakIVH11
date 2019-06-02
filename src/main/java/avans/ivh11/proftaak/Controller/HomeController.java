package avans.ivh11.proftaak.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("home/index");
    }

}
