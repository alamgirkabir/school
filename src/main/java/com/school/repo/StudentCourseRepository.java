/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.repo;

import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.StudentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alamgir
 */
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Long>{
    @Query("select sc from StudentCourseEntity sc where sc.student = ?1 and sc.course =?2")
    public StudentCourseEntity getByStudentAndCourse(StudentEntity student, CourseEntity course);
    
    @Query("select count(sc) from StudentCourseEntity sc where sc.student = ?1")
    public Long getNoOfEnrolledCourse(StudentEntity student);
    
    @Query("select sc from StudentCourseEntity sc where sc.student = ?1")
    public List<StudentCourseEntity> getEnrolledCourse(StudentEntity student);
    
    
    @Query("select count(sc) from StudentCourseEntity sc where sc.course = ?1")
    public Long getNoOfEnrolledStudent(CourseEntity course);
}
