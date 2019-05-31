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
    @ManyToOne(cascade = CascadeType.ALL)
    private Student mealCook;

    @ManyToMany
    private Set<Dish> dishesList = new HashSet<>();

    @NotEmpty(message = "Summary is required")
    private String mealSummary;


    public String getDishNameByType(String dishType) {
        String starter = "Voorgerecht";
        String maincourse = "Hoofdgerecht";
        String desert = "Desert";

        for (Dish dish : dishesList) {
            if (dishType == dish.getDishType()) {
                return dish.getDishName();
            }
        }
        return null;

    }
}







//    private Student studentCook;
//
    //verantwoordelijkheid ligt bij de One kant
//    @OneToMany
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

