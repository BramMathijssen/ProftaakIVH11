package avans.ivh11.proftaak.Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    private String mealName;
}
