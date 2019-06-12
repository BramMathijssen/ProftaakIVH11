package avans.ivh11.proftaak.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class DecoratedStudent extends BaseStudent {

    @OneToOne
    protected BaseStudent baseStudent;

    protected DecoratedStudent(BaseStudent baseStudent) {
        this.baseStudent = baseStudent;
    }

}