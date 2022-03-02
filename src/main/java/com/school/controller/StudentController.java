/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.controller;

import com.school.controller.exception.CourseEnrolledException;
import com.school.controller.exception.NoRegistrationFoundException;
import com.school.controller.exception.ResourceNotFoundException;
import com.school.entity.CourseEntity;
import com.school.entity.StudentEntity;
import com.school.service.StudentService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @ApiOperation(value = "Fetch all students")
    @GetMapping
    public ResponseEntity getStudents() {
        try {
            List<StudentEntity> students = studentService.getStudents();
            if (students.size() <= 0) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(students);
            }
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public void addStudent(StudentEntity studentEntity) {
        studentService.addStudent(studentEntity);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity updateStudent(@PathVariable("studentId") Long studentId, StudentEntity studentEntity) {
        try {
            studentService.updateStudent(studentId, studentEntity);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable("studentId") Long studentId, StudentEntity studentEntity) {
        try {
            studentService.removeStudent(studentId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "API for students to register to courses")
    @PostMapping("/enroll")
    public ResponseEntity enrollCourse(Long studentId, Long courseId) {
        try {
            studentService.enrollCourse(studentId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Filter all courses with a specific student")
    @GetMapping("/enrolled_courses")
    public ResponseEntity getEnrolledCourses(Long studentId) {
        try {
            List<CourseEntity> courses = studentService.getEnrolledCourses(studentId);
            if (courses.size() <= 0) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(courses);
            }
        } catch (CourseEnrolledException | NoRegistrationFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Filter all students with a specific course")
    @GetMapping("/enrolled_students")
    public ResponseEntity getEnrolledStudents(Long courseId) {
        try {
            List<StudentEntity> students = studentService.getEnrolledStudents(courseId);
            if (students.size() <= 0) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(students);
            }
        } catch (CourseEnrolledException | NoRegistrationFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Filter all students with a no course")
    @GetMapping("/unenrolled_students")
    public ResponseEntity getStudentsWithNoCourse() throws Exception {
        try {
            List<StudentEntity> students = studentService.getStudentWithNocourse();
            if (students.size() <= 0) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(students);
            }
        } catch (CourseEnrolledException | NoRegistrationFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ApiOperation(value = "Filter all course with a no student")
    @GetMapping("/unenrolled_courses")
    public ResponseEntity getCourseWithNoStudents() throws Exception {
        try {
            List<CourseEntity> courses = studentService.getCourseWithNoStudent();
            if (courses.size() <= 0) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok().body(courses);
            }
        } catch (CourseEnrolledException | NoRegistrationFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
