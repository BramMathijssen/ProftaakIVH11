package avans.ivh11.proftaak.mocks;


import static org.junit.Assert.assertFalse;

import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.impl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceMockTest {

    private static final String STUDENT_NAME = "Karel";
    private static final Long STUDENT_ID = 23L;

    private static final String STUDENT_NAME2 = "Henk";
    private static final Long STUDENT_ID2 = 24L;

    //Mock the service dependencies(=StudentServiceImpl is dependent on studentRepo)
    @Mock
    StudentRepository studentRepository;

    //Mock the service which is to be tested (Can't be a interface)
    @InjectMocks
    StudentServiceImpl studentService;


    //https://hellokoding.com/spring-boot-test-service-layer-example-with-mockitos-mock-and-injectmock/
    @Test
    public void create(){

        //Arange
        Student student = createStudent(STUDENT_ID, STUDENT_NAME);

        List<Student> expectedStudents = Arrays.asList(student);

        //Act
        doReturn(expectedStudents).when(studentRepository).findAll(); //Argument passed to when has to be a Mock so dishRepository instead of dishService

        List<Student> actualStudents = studentService.findAll();

        //Assert
        assertThat(actualStudents).isEqualTo(expectedStudents);
    }


    @Test
    public void findById(){
        //Arrange
        Student student = createStudent(STUDENT_ID, STUDENT_NAME);

        //Act
        Mockito.when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));

        //Assert
        assertThat(studentService.findById(STUDENT_ID)).isEqualTo(Optional.of(student));

    }

    @Test
    public void findAllStudents(){
        //Arrange
        Student student1 = createStudent(STUDENT_ID , STUDENT_NAME);
        Student student2 = createStudent(STUDENT_ID2, STUDENT_NAME2);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        //Act
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);

        //Assert
        assertThat(studentService.findAll()).isEqualTo(studentList);
    }

    @Test
    public void deleteStudent(){
        //Arrange
        Student student = createStudent(STUDENT_ID, STUDENT_NAME);

        //Act
        Mockito.when(studentRepository.findById(STUDENT_ID)).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.existsById(student.getId())).thenReturn(false);

        //Assert
        assertFalse(studentRepository.existsById(student.getId()));

    }

    private Student createStudent(Long id, String studentName) {
        Student student = new Student();
        student.setId(id);
        student.setStudentName(studentName);

        return student;
    }


}
