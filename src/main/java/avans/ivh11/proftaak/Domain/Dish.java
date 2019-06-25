package avans.ivh11.proftaak.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Version;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.*;

@Entity
@Getter
@Setter
//@ToString(callSuper = true, includeFieldNames = true, of = { "dishName", "dishType", "dishRecipe" })
@NoArgsConstructor
public class Dish  {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    private Calendar created = Calendar.getInstance();

    @NotEmpty(message = "Dish Name is required")
    private String dishName;

    @NotEmpty(message = "Dish Type is required")
    private String dishType;

    private String dishRecipe;

    //Default Lazy
    @ManyToMany(mappedBy = "dishesList" ,cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Meal> mealsList = new HashSet<>();

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


}
