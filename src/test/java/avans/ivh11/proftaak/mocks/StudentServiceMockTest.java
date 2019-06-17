package avans.ivh11.proftaak.mocks;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.StudentService;
import avans.ivh11.proftaak.Service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceMockTest {

    private static final String STUDENT_NAME = "Henkie";

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void create2(){
//        Student student = new Student();
//        student.setStudentName("Gekkie");
//
//        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
//
//        Student created = studentService.save(student);
//
//        assertThat(created.getStudentName()).isSameAs(student.getStudentName());




        //Student student = createStudent(STUDENT_NAME);

        Student student = MakeStudent("Henk");

        List<Student> expectedProducts = Arrays.asList(student);

        doReturn(expectedProducts).when(studentService).findAll();

        // when
        List<Student> actualProducts = studentService.findAll();

        // then
        assertThat(actualProducts).isEqualTo(expectedProducts);
    }

    private Student MakeStudent(String studentName){
        Student student = new Student();
        student.setStudentName(studentName);
        studentService.save(student);
        return student;
    }

//    private Student createStudent(String studentName) {
//        Student student = new Student();
//        student.setStudentName(studentName);
//        Student retval = studentService.save(student);
//        assertNotNull(retval);
//        assertNotNull(retval.getId());
//        assertEquals("studentName", studentName, retval.getStudentName());
//        return retval;
//    }


}
