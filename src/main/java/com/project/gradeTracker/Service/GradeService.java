package com.project.gradeTracker.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.gradeTracker.Entity.Grade;
import com.project.gradeTracker.Entity.Student;
import com.project.gradeTracker.Repository.GradeRepository;
import com.project.gradeTracker.Repository.StudentRepository;

@Service
public class GradeService {
     @Autowired
     private GradeRepository gradeRepository;
     @Autowired
     private StudentRepository studentRepository;
     public List<Grade>getGradesByStudentId(Long studentId){
    	 return studentRepository.findById(studentId)
    			 .map(Student::getGrades)
    			 .orElseThrow( ()-> new IllegalArgumentException("Invalid Student ID"));
     }
     
     public Grade saveGrade(Grade grade,Long studentId) {
    	 Student student =studentRepository.findById(studentId)
    			 .orElseThrow(()-> new IllegalArgumentException("Invalid Student ID"));
    	          grade.setStudent(student);
    	          return gradeRepository.save(grade);
     }
     public double calculateAverage(Long studentId) {
    	 List<Grade> grades=getGradesByStudentId(studentId);
    	 return grades.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
    	 
     }
     public char calculateLetterGrade(double average) {
    	 if(average>=90) return 'A';
    	 if(average>=90) return 'B';
    	 if(average>=90) return 'C';
    	 if(average>=90) return 'D';
    	 return 'F';	 
     }
     
     public double calculateGPA(Long studentId) {
    	 double average=calculateAverage(studentId);
    	 char letterGrade=calculateLetterGrade(average);
    	 Map<Character,Double>gpaMap=Map.of('A', 4.0, 'B', 3.0, 'C', 2.0, 'D', 1.0, 'F', 0.0);
    	 return gpaMap.get(letterGrade);
     }
     
}
