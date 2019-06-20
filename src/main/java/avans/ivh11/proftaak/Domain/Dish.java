package avans.ivh11.proftaak.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.*;

@Entity
@Getter
@Setter
@ToString(callSuper = true, includeFieldNames = true, of = { "dishName", "dishType", "dishRecipe" })
@NoArgsConstructor
public class Dish extends DomainObject {

    @Id
    @GeneratedValue
    private Long id;

    private Calendar created = Calendar.getInstance();

    @NotEmpty(message = "Dish Name is required")
    private String dishName;

    @NotEmpty(message = "Dish Type is required")
    private String dishType;

    private String dishRecipe;

    @ManyToMany(mappedBy = "dishesList")
    private Set<Meal> mealsList = new HashSet<>();
    //private List<Meal> meals = new ArrayList<>();


    public String getDishName() {
        return dishName;
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


//    could multiple dishPrice times amount of guests and store it as mealPrice total
//    @NotNull(message = "Price is required.")
//    private int dishPrice;


}
