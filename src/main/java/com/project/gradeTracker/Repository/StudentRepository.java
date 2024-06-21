package com.project.gradeTracker.Repository;

import org.springframework.data.repository.CrudRepository;

import com.project.gradeTracker.Entity.Student;

public interface StudentRepository  extends CrudRepository<Student,Long>{

}
