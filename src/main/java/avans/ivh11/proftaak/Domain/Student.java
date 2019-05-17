package avans.ivh11.proftaak.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String studentName;

    //private boolean isMealCook;

    //private boolean isMealGuest;

    //Getters & Setters

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

//    public boolean isMealCook() {
//        return isMealCook;
//    }

//    public boolean isMealGuest() {
//        return isMealGuest;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

//    public void setMealCook(boolean mealCook) {
//        isMealCook = mealCook;
//    }
//
//    public void setMealGuest(boolean mealGuest) {
//        isMealGuest = mealGuest;
//    }
}
