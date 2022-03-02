/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.repo;

import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.StudentCourseKey;
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
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, StudentCourseKey> {

    @Query("select sc from StudentCourseEntity sc where sc.id.studentId = ?1 and sc.id.courseId =?2")
    public StudentCourseEntity getByStudentAndCourse(Long studentId, Long courseId);

    @Query("select count(sc) from StudentCourseEntity sc where sc.id.studentId = ?1")
    public Long getNoOfEnrolledCourse(Long studentId);

    @Query("select sc.course from StudentCourseEntity sc where sc.id.studentId = ?1")
    public List<CourseEntity> getEnrolledCourse(Long studentId);

    @Query("select count(sc) from StudentCourseEntity sc where sc.id.courseId = ?1")
    public Long getNoOfEnrolledStudent(Long courseId);

    @Query("select sc.student from StudentCourseEntity sc where sc.id.courseId = ?1")
    public List<StudentEntity> getEnrolledStudent(Long courseId);
    
    @Query(value="select student.id from StudentEntity student left join StudentCourseEntity sc on sc.id.studentId = student.id where sc.id.studentId is null" )
    public List<Long> getStudentsWithNoCourse();

    @Query(value="select course.id from CourseEntity course left join StudentCourseEntity sc on sc.id.courseId = course.id where sc.id.courseId is null" )
    public List<Long> getCourseWithNoStudent();
    
    
}
