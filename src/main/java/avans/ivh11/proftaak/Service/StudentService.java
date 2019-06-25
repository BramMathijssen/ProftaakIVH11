package avans.ivh11.proftaak.Service;

import avans.ivh11.proftaak.Domain.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {

    List<Student> findAll();

    Student save(Student student);

    Optional<Student> findById(Long id);

    void deleteById(Long id);



}
