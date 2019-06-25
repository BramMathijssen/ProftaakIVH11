package avans.ivh11.proftaak.Service;

import avans.ivh11.proftaak.Domain.Student;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    List<Student> findAll();

    Student save(Student student);

    Optional<Student> findById(Long id);

    void deleteById(Long id);



}
