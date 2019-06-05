package avans.ivh11.proftaak.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class SecurityController {

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("security/login");
    }


}