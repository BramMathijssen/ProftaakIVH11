package avans.ivh11.proftaak.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Calendar;

@Entity
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String mealName;

    private int mealPrice;

    private String mealType;

    private String mealSummary;

    private Calendar created = Calendar.getInstance();


//    private Student studentCook;
//
//    private ArrayList<Student> studentGuestList;


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(int mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealSummary() {
        return mealSummary;
    }

    public void setMealSummary(String mealSummary) {
        this.mealSummary = mealSummary;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getCreated() {
        return created;
    }
}
