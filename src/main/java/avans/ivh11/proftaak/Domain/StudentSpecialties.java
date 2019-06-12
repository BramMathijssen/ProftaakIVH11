package avans.ivh11.proftaak.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentSpecialties extends DecoratedStudent {

    private boolean isVegetarian;
    private String allergies;

    public StudentSpecialties(boolean isVegetarian, String allergies, BaseStudent student){
        super(student);
        this.isVegetarian = isVegetarian;
        this.allergies = allergies;
    }
}
