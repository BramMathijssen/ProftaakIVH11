package avans.ivh11.proftaak.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping(value = "/403")
    public String error4043() {
        return "/errors/403";
    }

}
