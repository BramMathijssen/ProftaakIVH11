package avans.ivh11.proftaak.Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String studentName;
}
