/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.controller;

import com.school.controller.exception.CourseEnrolledException;
import com.school.controller.exception.NoRegistrationFoundException;
import com.school.entity.CourseEntity;
import com.school.entity.StudentEntity;
import com.school.service.CourseService;
import com.school.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alamgir
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public void addStudent(StudentEntity studentEntity) {
        studentService.addStudent(studentEntity);
    }
    
    @PostMapping("/enroll")
    public ResponseEntity enrollCourse(Long studentId, Long courseId) {
        try{
            studentService.enrollCourse(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
    
    @GetMapping("/enrolled_courses")
    public ResponseEntity getEnrolledCourses(Long studentId){
        try{
            List<CourseEntity> courses = studentService.getEnrolledCourses(studentId);
            if(courses.size() <= 0){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok().body(courses);
            }
        }
        catch(CourseEnrolledException | NoRegistrationFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
