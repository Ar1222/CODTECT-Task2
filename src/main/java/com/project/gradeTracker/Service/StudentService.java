package com.project.gradeTracker.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gradeTracker.Entity.Student;
import com.project.gradeTracker.Repository.StudentRepository;

@Service
public class StudentService {
   @Autowired
   private StudentRepository  studentRepository;
   public List<Student> getAllStudents(){
	return (List<Student>) studentRepository.findAll();
   }
   
   public Optional<Student>getStudentById(Long id){
	   return studentRepository.findById(id);
   }
    
   public Student saveStudent(Student student) {
	   return studentRepository.save(student);
   }
}
