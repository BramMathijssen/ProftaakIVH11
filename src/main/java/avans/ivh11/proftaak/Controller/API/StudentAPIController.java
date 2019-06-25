package avans.ivh11.proftaak.Controller.API;

import avans.ivh11.proftaak.Controller.MealController;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")

public class StudentAPIController {

    private final Logger logger = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        if (!student.isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(student.get());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        studentService.deleteById(id);
        logger.debug("Deleted Student with id " + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {
        if (!studentService.findById(id).isPresent()) {
            logger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(studentService.save(student));
    }
}
