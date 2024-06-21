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

import com.project.gradeTracker.Entity.Grade;
import com.project.gradeTracker.Entity.Student;
import com.project.gradeTracker.Service.GradeService;
import com.project.gradeTracker.Service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students/{studentId}/grades")
public class GradeController {
  @Autowired
  private GradeService gradeService;
  @Autowired
  private StudentService studentService;
  
  @GetMapping
  public String getGradeByStudentId(@PathVariable Long studentId,Model model) {
	  List<Grade>grades=gradeService.getGradesByStudentId(studentId);
	  double average=gradeService.calculateAverage(studentId);
	  char letterGrade=gradeService.calculateLetterGrade(average);
	  double gpa=gradeService.calculateGPA(studentId);
	  Student student=studentService.getStudentById(studentId)
			  .orElseThrow(()->new IllegalArgumentException("Invalid student ID"));
	  model.addAttribute("student",student);
	  model.addAttribute("grades",grades);
	  model.addAttribute("average",average);
	  model.addAttribute("letterGrade",letterGrade);
	  model.addAttribute("gpa",gpa);
	  return "grades";    
  }
  @PostMapping
  public String createGrade(@Valid @ModelAttribute Grade grade,@PathVariable Long studentId) {
	  gradeService.saveGrade(grade, studentId);
	  return "redirect:/students/"+studentId+"/grades";
  }
  
}
