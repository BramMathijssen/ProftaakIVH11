package avans.ivh11.proftaak.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Calendar;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String studentName;

    private Calendar created = Calendar.getInstance();


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

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {

        this.created = created;
    }


//    public void setMealCook(boolean mealCook) {
//        isMealCook = mealCook;
//    }
//
//    public void setMealGuest(boolean mealGuest) {
//        isMealGuest = mealGuest;
//    }
}
