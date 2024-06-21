package com.project.gradeTracker.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message=" Subject is mandatory")
	private String Subject;
	
	@Min(0)
	@Max(100)
	private double score;
	@ManyToOne
	@JoinColumn(name=" student_id")
	private Student student;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	 
    
}
