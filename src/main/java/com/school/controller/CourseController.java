/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.controller;

import com.school.entity.CourseEntity;
import com.school.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping("/")
    public List<CourseEntity> getCourses(){
        return courseService.getCourses();
    }
    
    @PostMapping("/add")
    public void addCourses(CourseEntity course){
        courseService.addCourse(course);
    }
    
    
}
