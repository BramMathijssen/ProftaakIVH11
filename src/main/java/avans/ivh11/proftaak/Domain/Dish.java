package avans.ivh11.proftaak.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Dish {

    @Id
    @GeneratedValue
    private Long id;

    private Calendar created = Calendar.getInstance();

    @NotEmpty(message = "Dish Name is required")
    private String dishName;

    @NotEmpty(message = "Dish Type is required")
    private String dishType;

    private String dishRecipe;

    @ManyToMany(mappedBy = "dishesList")
    private Set<Meal> mealsList = new HashSet<>();
    //private List<Meal> meals = new ArrayList<>();

//    could multiple dishPrice times amount of guests and store it as mealPrice total
//    @NotNull(message = "Price is required.")
//    private int dishPrice;

}
