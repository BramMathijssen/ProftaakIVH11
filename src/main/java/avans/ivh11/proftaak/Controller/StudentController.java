package avans.ivh11.proftaak.Controller;


import avans.ivh11.proftaak.Domain.BaseStudent;
import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Domain.StudentSpecialties;
import avans.ivh11.proftaak.Repository.BaseStudentRepository;


import avans.ivh11.proftaak.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/s")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public ModelAndView list(){
        Iterable<Student> students = this.studentRepository.findAll();
        return new ModelAndView("students/list" , "students", students);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Student student) {
        return new ModelAndView("students/view", "student", student);
    }

    @GetMapping(params = "form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(@ModelAttribute Student student, Model model) {
        BaseStudent student2 = new Student();
        model.addAttribute("student2" , student2);
        StudentSpecialties studspec = new StudentSpecialties();
        model.addAttribute("studspec" , studspec);
        return "students/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView create(@Valid Student student, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("students/form", "formErrors", result.getAllErrors());
        }
        student = this.studentRepository.save(student);
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