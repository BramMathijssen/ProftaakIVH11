package avans.ivh11.proftaak.Domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private Calendar created = Calendar.getInstance();

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student mealCook;

    @ManyToMany
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



