package avans.ivh11.proftaak.Service.impl;


import avans.ivh11.proftaak.Domain.Student;
import avans.ivh11.proftaak.Repository.StudentRepository;
import avans.ivh11.proftaak.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;



    public List<Student> findAll() {
        // MySQL and H2 return the restaurants of findAll() in different order
        // sorting the result makes the behavior less database vendor dependent
        return Lists.newArrayList(studentRepository.findAll());
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

}





