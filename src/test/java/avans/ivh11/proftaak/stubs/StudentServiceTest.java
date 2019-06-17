package avans.ivh11.proftaak.stubs;

import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.StudentRepository;
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

    private static final String STUDENT_NAME = "Henkie";

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeClass
    public static void initializeDatabase() {
    }

    @AfterClass
    public static void cleanUpDatabase() {
    }

//    @Test
//    public void create2(){
//
//        Student student = createStudent(STUDENT_NAME);
//
//        List<Student> expectedProducts = Arrays.asList(student);
//
//        doReturn(expectedProducts).when(studentService).findAll();
//
//        // when
//        List<Product> actualProducts = productService.findAll();
//
//        // then
//        assertThat(actualProducts).isEqualTo(expectedProducts);
//    }

    @Test
    public void createStudent(){
        //execute
        Student student = new Student();
        student.setStudentName(STUDENT_NAME);
        studentService.save(student);

        List<Student> students = studentService.findAll();
        assertNotNull(students);
        assertTrue("created a student" ,students.contains(student));
    }

    @Test
    public void findById(){
        Student student = new Student();
        student.setStudentName(STUDENT_NAME);

        studentService.save(student);

        Long studentId = student.getId();


        Optional<Student> stud = studentService.findById(studentId);

        //assertTrue("Same Id" , student.getId().equals(stud.get(id));
    }

    @Test
    public void delete() {
        // prepare
        Student student = createStudent(STUDENT_NAME);
        List<Student> students = this.studentService.findAll();
        assertNotNull(students);
        assertTrue("created customer in list", students.contains(student));

        // execute
        studentService.deleteById(student.getId());

        // verify
        List<Student> students2 = studentService.findAll();
        assertNotNull(students2);
        assertFalse("deleted customer not in the list", students2.contains(student));
    }

//    @Test
//    public void create(){
//        //execute
//        Student student = createStudent(STUDENT_NAME);
//
//        //verify
//        List<Student> students = studentService.findAll();
//        assertNotNull(students);
//        assertTrue("created a student" ,students.contains(student));
//    }

//    @Test
//    public void findById() {
//        // prepare
//        Student student = createStudent(STUDENT_NAME);
//
//        // execute
//        Student student2 = studentService.findById(student.getId());
//
//        // verify
//        assertTrue("created customer in findByFirstNameAndLastName", student.equals(student2));
//    }


    private Student createStudent(String studentName) {
        Student student = new Student();
        student.setStudentName(studentName);
        Student retval = studentService.save(student);
//        assertNotNull(retval);
//        assertNotNull(retval.getId());
//        assertEquals("studentName", studentName, retval.getStudentName());
        return retval;
    }

}
