package avans.ivh11.proftaak.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue
    private Long id;

//    @Column(nullable = false, unique = true)
//    private String userName;
//
//    private String password;

    @NotEmpty(message = "Student Name is required.")
    private String studentName;

    private Calendar created = Calendar.getInstance();

    //@ManyToOne
    //private Meal meals;
    //displays meal_id NULL in the database.
    //can implement a list of meals with a @OneToMany relationship to couple multiple meals to 1 student to check in the view.
    //@JsonBackReference
    //@JoinColumn(name = "meal_id")
    @OneToMany(
            mappedBy = "mealCook",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<Meal> meals = new HashSet<>();

    //private boolean isMealCook;

    //private boolean isMealGuest;

    public int getAmountOfMeals() {
        int count = 0;
        for (Meal meal : meals) {
            count++;
        }
        return count;
    }


}



