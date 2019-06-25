package avans.ivh11.proftaak.Controller;

import avans.ivh11.proftaak.Domain.IngredientsSupply;
import avans.ivh11.proftaak.Repository.IngredientsSupplyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {

    private IngredientsSupplyRepository supplyRepository;

    public HomeController(IngredientsSupplyRepository supplyRepository){
        this.supplyRepository = supplyRepository;
    }

    @GetMapping
    public ModelAndView index(){
        //createSupplies();


        return new ModelAndView("home/index");
    }

    //To create new supplies add this method to the index method().
    public void createSupplies(){

        IngredientsSupply supply = new IngredientsSupply.Builder()
                .ingredientName("Olijfolie")
                .amountMl(1000)
                .build();

        supplyRepository.save(supply);


        IngredientsSupply supply2 = new IngredientsSupply.Builder()
                .ingredientName("Knolraap")
                .pieces(1)
                .build();

        supplyRepository.save(supply2);

        //System.exit(0);



    }

}
