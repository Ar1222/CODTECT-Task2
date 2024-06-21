package com.project.gradeTracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.gradeTracker.Entity.Student;
import com.project.gradeTracker.Service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
 @Autowired
 private StudentService studentService;
 @GetMapping
 public String getAllStudents(Model model) {
	 List<Student>students=studentService.getAllStudents();
	 model.addAttribute("students",students);
	 return "index";
 }
 @GetMapping("/{id}")
 public String getStudentById(@PathVariable Long id,Model model) {
	 return studentService.getStudentById(id).map(student->{
		 model.addAttribute("student",student);
		 return "student";}).orElse("redirect:/students");
 }
 @PostMapping
 public String createStudent(@Valid @ModelAttribute Student student) {
	 studentService.saveStudent(student);
	 return "redirect:/students";
 }
}
