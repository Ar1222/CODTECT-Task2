package com.project.gradeTracker.Repository;

import org.springframework.data.repository.CrudRepository;

import com.project.gradeTracker.Entity.Grade;

public interface GradeRepository extends CrudRepository<Grade,Long> {

}
