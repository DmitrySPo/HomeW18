package org.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.model.Student;
import org.example.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("")
    public String showStudents(Model model) {
        List<Student> students = (List<Student>) studentRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam int age, @RequestParam String diplomaTopic) {
        Student student = new Student(firstName, lastName, age, diplomaTopic);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет такого студента"));
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName,
                                @RequestParam int age, @RequestParam String diplomaTopic) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Нет такого студента"));
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setDiplomaTopic(diplomaTopic);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
}
