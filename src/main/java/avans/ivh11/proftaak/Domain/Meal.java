package avans.ivh11.proftaak.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;

@Entity
@Getter
@Setter
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    //@NotBlank(message = "Name is mandatory")
    private String mealName;

    @NotNull(message = "Price is required.")
    @Min(value = 0 , message = "Must be larger than 0") // at least 0
    private int mealPrice;

    private String mealType;

    private String mealSummary;

    private Calendar created = Calendar.getInstance();

    @OneToOne
    @JoinColumn(name = "meal_cook_id")
    private Student mealCook;

//    private Student studentCook;
//
//    private ArrayList<Student> studentGuestList;


    //Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getMealName() {
//        return mealName;
//    }
//
//    public void setMealName(String mealName) {
//        this.mealName = mealName;
//    }
//
//    public int getMealPrice() {
//        return mealPrice;
//    }
//
//    public void setMealPrice(int mealPrice) {
//        this.mealPrice = mealPrice;
//    }
//
//    public String getMealType() {
//        return mealType;
//    }
//
//    public void setMealType(String mealType) {
//        this.mealType = mealType;
//    }
//
//    public String getMealSummary() {
//        return mealSummary;
//    }
//
//    public void setMealSummary(String mealSummary) {
//        this.mealSummary = mealSummary;
//    }
//
//    public void setCreated(Calendar created) {
//        this.created = created;
//    }
//
//    public Calendar getCreated() {
//        return created;
//    }
//
//    public void setMealCook(Student mealCook) {
//        this.mealCook = mealCook;
//    }
//
//    public Student getMealCook() {
//
//        return mealCook;
//    }
}
