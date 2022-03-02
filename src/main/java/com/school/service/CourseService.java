/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.service;

import com.school.controller.exception.ResourceNotFoundException;
import com.school.entity.CourseEntity;
import com.school.entity.StudentEntity;
import com.school.repo.CourseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alamgir
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void addCourse(CourseEntity course) {
        courseRepository.save(course);
    }

    public List<CourseEntity> getCourses() {
        return courseRepository.findAll();
    }

    public void updateCourse(Long studentId, CourseEntity studentEntity) throws ResourceNotFoundException {
        Optional<CourseEntity> studentOptional = courseRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        studentEntity.setId(studentId);
        courseRepository.save(studentEntity);
    }

    public void removeCourse(Long studentId) throws ResourceNotFoundException {
        Optional<CourseEntity> studentOptional = courseRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        courseRepository.deleteById(studentId);
    }
}
