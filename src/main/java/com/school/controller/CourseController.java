/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.controller;

import com.school.controller.exception.ResourceNotFoundException;
import com.school.entity.CourseEntity;
import com.school.service.CourseService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author alamgir
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    
    @ApiOperation(value = "Fetch all courses")
    @GetMapping
    public List<CourseEntity> getCourses(){
        return courseService.getCourses();
    }
    
    @PostMapping("/add")
    public void addCourses(CourseEntity course){
        courseService.addCourse(course);
    }
    
        
    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable("courseId") Long courseId, @RequestBody CourseEntity courseEntity) {
        try{
            courseService.updateCourse(courseId, courseEntity);
            return ResponseEntity.ok().build();
        }
        catch(ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable("courseId") Long courseId) {
        try{
            courseService.removeCourse(courseId);
            return ResponseEntity.ok().build();
        }
        catch(ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
        }
    }
    
}
