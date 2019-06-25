package avans.ivh11.proftaak.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student extends BaseStudent  {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Student Name is required.")
    private String studentName;

    private Calendar created = Calendar.getInstance();


    //Default lazy
    @OneToMany(
            mappedBy = "mealCook",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<Meal> meals = new HashSet<>();


    public Student(String studentName){
        this.studentName = studentName;
    }


    public int getAmountOfMeals() {
        int count = 0;
        for (Meal meal : meals) {
            count++;
        }
        return count;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // null is not an instance of anything, so check whether null is not
        // necessary
        if (!(o instanceof DomainObject)) {
            return false;
        }

        DomainObject other = (DomainObject) o;

        // unsaved objects are never equal
        if (id == null || other.getId() == null) {
            return false;
        }

        return id.equals(other.getId());
    }


}



