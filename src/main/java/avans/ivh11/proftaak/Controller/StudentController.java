package avans.ivh11.proftaak.Controller;


import avans.ivh11.proftaak.AOP.LoggerSingleton;
import avans.ivh11.proftaak.Domain.BaseStudent;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Domain.StudentSpecialties;
import avans.ivh11.proftaak.Repository.BaseStudentRepository;


import avans.ivh11.proftaak.Repository.StudentSpecialtiesRepository;
import avans.ivh11.proftaak.Service.impl.StudentServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/s")
public class StudentController {

    private final BaseStudentRepository studentRepository;
    private final StudentSpecialtiesRepository studentSpecialtiesRepository;
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService,BaseStudentRepository studentRepository, StudentSpecialtiesRepository studentSpecialtiesRepository){
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.studentSpecialtiesRepository = studentSpecialtiesRepository;
    }

    @GetMapping
    public ModelAndView list(Student student){
        createStudent();
        decorateStudent();
        Iterable<Student> students = studentService.findAll();
        return new ModelAndView("students/list" , "students", students);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Student student) {

        LoggerSingleton.getSingletonInstance().logSingleton("TEST FROM VIEW");

        return new ModelAndView("students/view", "student", student);
    }

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(@ModelAttribute Student student, Model model) {
        return "students/form";
    }

    @GetMapping("specialties/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createSpecialtiesForm(@PathVariable("id") Student student, Model model) {
        StudentSpecialties studSpec = new StudentSpecialties();
        model.addAttribute("studSpec" , studSpec);
        return new ModelAndView("students/specialtiesForm", "student", student);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@Valid Student student, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("students/form", "formErrors", result.getAllErrors());
        }
          //Serializable test: Running create() while createStudent() is running
//        List<Student> students = studentService.findAll();
//
//        System.out.println("#students  made: " +
//                students.size());

        student = this.studentRepository.save(student);

        //specialties = this.studentSpecialtiesRepository.save(specialties);
        redirect.addFlashAttribute("globalMessage", "view.success");
        return new ModelAndView("redirect:/s/{student.id}", "student.id", student.getId());
    }

    @RequestMapping("foo")
    public String foo() {
        throw new RuntimeException("Expected exception in controller");
    }

    @GetMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.studentRepository.deleteById(id);

        Iterable<Student> students = this.studentService.findAll();
        return new ModelAndView("students/list", "students", students);
    }


    @GetMapping("modify/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView modifyForm(@PathVariable("id") Student student) {
        return new ModelAndView("students/form", "student", student);
    }

    public void createStudent(){
        Student student = new Student("Henk");

          //Test input for ACID(Isolation)
//        List<Student> students = studentService.findAll();
//
//        System.out.println("#students  made: " +
//                students.size());

        studentRepository.save(student);

         //Test input for ACID(Isolation)
//        try {
//            Thread.sleep(10000);
//        } catch(InterruptedException e) {}

        //System.exit(0);
    }

    private void decorateStudent(){
        Optional<BaseStudent> concreteStudent = studentRepository.findById(1L);
        BaseStudent baseStudent1 = new StudentSpecialties(true, "Zuivel", concreteStudent.get());
        studentRepository.save(baseStudent1);
    }


}