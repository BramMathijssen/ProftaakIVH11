package avans.ivh11.proftaak.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Student Name is required.")
    private String studentName;

    private Calendar created = Calendar.getInstance();

    //@OneToMany(mappedBy = "mealCook")
    //displays meal_id NULL in the database.
    //can implement a list of meals with a @OneToMany relationship to couple multiple meals to 1 student to check in the view.
    @JsonBackReference
    @ManyToOne
    private Meal meal;


    //private boolean isMealCook;

    //private boolean isMealGuest;

    //Getters & Setters

//    public Long getId() {
//        return id;
//    }
//
//    public String getStudentName() {
//        return studentName;
//    }
//
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setStudentName(String studentName) {
//        this.studentName = studentName;
//    }
//
//    public Calendar getCreated() {
//        return created;
//    }
//
//    public void setCreated(Calendar created) {
//
//        this.created = created;
//    }
//
//    public void setMeal(Meal meal) {
//        this.meal = meal;
//    }
//
//    public Meal getMeal() {
//
//        return meal;
//    }



    //    public boolean isMealCook() {
//        return isMealCook;
//    }

//    public boolean isMealGuest() {
//        return isMealGuest;
//    }
}
