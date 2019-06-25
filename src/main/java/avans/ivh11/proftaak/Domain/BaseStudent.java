package avans.ivh11.proftaak.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseStudent extends DomainObject {

    @Id
    @GeneratedValue
    private Long id;




}
