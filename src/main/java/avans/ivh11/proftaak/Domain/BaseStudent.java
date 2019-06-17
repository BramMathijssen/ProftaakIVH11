package avans.ivh11.proftaak.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public abstract class BaseStudent {

    @Id
    @GeneratedValue
    private Long id;
}
