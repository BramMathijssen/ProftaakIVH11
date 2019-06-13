package avans.ivh11.proftaak.Controller;


import avans.ivh11.proftaak.AOP.LoggerSingleton;
import avans.ivh11.proftaak.Domain.BaseStudent;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Domain.StudentSpecialties;
import avans.ivh11.proftaak.Repository.BaseStudentRepository;


import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Repository.StudentSpecialtiesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

import javax.validation.Valid;


@Controller
@RequestMapping("/s")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentSpecialtiesRepository studentSpecialtiesRepository;

    public StudentController(StudentRepository studentRepository, StudentSpecialtiesRepository studentSpecialtiesRepository){
        this.studentRepository = studentRepository;
        this.studentSpecialtiesRepository = studentSpecialtiesRepository;
    }

    @GetMapping
    public ModelAndView list(){
        Iterable<Student> students = this.studentRepository.findAll();
        return new ModelAndView("students/list" , "students", students);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Student student) {
        //System.out.println("TEST");
        LoggerSingleton.getSingletonInstance().logSingleton("TEST FROM VIEW");

        //LoggerSingleton.getInstance().log("test");
        return new ModelAndView("students/view", "student", student);
    }

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(@ModelAttribute Student student, Model model) {
        BaseStudent student2 = new Student();
        BaseStudent student3 = new StudentSpecialties();
        model.addAttribute("student2" , student2);
        model.addAttribute("student3" , student3);
        StudentSpecialties studspec = new StudentSpecialties();
        model.addAttribute("studspec" , studspec);
        return "students/form";
    }

//    @GetMapping(params = "specialtiesForm")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String createSpecialtiesForm2(@ModelAttribute Student student, Model model) {
//        StudentSpecialties studSpec = new StudentSpecialties();
//        model.addAttribute("studSpec" , studSpec);
//        return "students/list";
//    }

    @GetMapping("specialties/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createSpecialtiesForm(@PathVariable("id") Student student, Model model) {
        StudentSpecialties studSpec = new StudentSpecialties();
        model.addAttribute("studSpec" , studSpec);
        return new ModelAndView("students/specialtiesForm", "student", student);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@Valid Student student, StudentSpecialties specialties, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("students/form", "formErrors", result.getAllErrors());
        }
        student = this.studentRepository.save(student);
        specialties = this.studentSpecialtiesRepository.save(specialties);
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

        Iterable<Student> students = this.studentRepository.findAll();
        return new ModelAndView("students/list", "students", students);
    }

    @GetMapping("modify/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView modifyForm(@PathVariable("id") Student student) {
        return new ModelAndView("students/form", "student", student);
    }


}