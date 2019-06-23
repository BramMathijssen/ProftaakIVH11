package avans.ivh11.proftaak.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Student extends BaseStudent {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Student Name is required.")
    private String studentName;

    private Calendar created = Calendar.getInstance();


    @OneToMany(
            mappedBy = "mealCook",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<Meal> meals = new HashSet<>();


    public int getAmountOfMeals() {
        int count = 0;
        for (Meal meal : meals) {
            count++;
        }
        return count;
    }

}



