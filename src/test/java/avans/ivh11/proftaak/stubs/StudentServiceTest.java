package avans.ivh11.proftaak.stubs;

import avans.ivh11.proftaak.Domain.Dish;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.DishService;
import avans.ivh11.proftaak.Service.StudentService;
import avans.ivh11.proftaak.Service.impl.StudentServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class StudentServiceTest {

    private static final String STUDENT_NAME = "Henk";

    private static final String STUDENT_NAME2 = "Karel";

    @Autowired
    private StudentService studentService;

    @Test
    public void create(){
        //execute
        Student student = createStudent2(STUDENT_NAME);

        //verify
        List<Student> students = studentService.findAll();
        assertNotNull(students);
        assertTrue("created a student" ,students.contains(student));
    }


    @Test
    public void findById(){
        //execute
        Student student = createStudent2(STUDENT_NAME);

        Long studentId = student.getId();

        Optional<Student> student2 = studentService.findById(studentId);
        //verify
        assertNotNull(student2); //Not necessary because a Optional is not Null
        //assertEquals(Optional.of(dish2), dish);
        assertEquals("created a student", student2.get(), student);
    }

    @Test
    public void findBy_invalidId(){
        //execute
        Student student = createStudent2(STUDENT_NAME);

        Long studentId = student.getId()+1L;

        Optional<Student> student2 = studentService.findById(studentId);

        //verify
        if(student2.isPresent()){
            assertFalse("Student found", true);
        }else{
            assertTrue("Student not found", true);
        }
    }

    @Test
    public void delete() {
        // prepare
        Student student = createStudent2(STUDENT_NAME);
        List<Student> students = studentService.findAll();
        assertNotNull(students);
        assertTrue("created dish in list", students.contains(student));

        // execute
        studentService.deleteById(student.getId());

        // verify
        List<Student> student2 = studentService.findAll();
        assertNotNull(student2);
        assertFalse("deleted student not in the list", student2.contains(student));
    }


    @Test
    public void update() {
        Student student = createStudent2(STUDENT_NAME);
        student.setStudentName(STUDENT_NAME2);

        studentService.save(student);

        Optional<Student> student2 = studentService.findById(student.getId());

        assertEquals("studentName", STUDENT_NAME2, student2.get().getStudentName());
    }

    @Test
    public void update_invalid_dish() {
        Student student = new Student();
        student.setId(1L);
        student.setStudentName("Henk");

        studentService.save(student);

        Optional<Student> student2 = studentService.findById(student.getId()+1L);

        if(student2.isPresent()){
            student2.get().setStudentName("Pieter");
            assertFalse("studentName", STUDENT_NAME.equals(student2.get().getStudentName()));
        }
        else{
            assertFalse("Student was not found, so not updated" , student2.isPresent() );
        }
    }


    private Student createStudent2(String studentName) {
        Student student = new Student();
        student.setStudentName(studentName);
        Student retval = studentService.save(student);
        return retval;
    }
}
