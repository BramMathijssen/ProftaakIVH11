package avans.ivh11.proftaak.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meal extends DomainObject {

    @Id
    @GeneratedValue
    private Long id;

    private Calendar created = Calendar.getInstance();

    //Can't have Fetchtype Lazy because it brings complications with the PUT/POST methods from the API
    @JoinColumn(name = "student_id")
    @ManyToOne
    @NotNull(message = "Please enter a cook")
    private Student mealCook;

    //Default Lazy.
    @ManyToMany
    @NotEmpty(message="Atleast enter one dish")
    private Set<Dish> dishesList = new HashSet<>();

    @NotEmpty(message = "Summary is required")
    private String mealSummary;


    public String getDishNameByType(String dishType) {

        for (Dish dish : dishesList) {
            if ((dishType.equals(dish.getDishType()))) {
                return dish.getDishName();
            }
        }
        return null;

    }
}



