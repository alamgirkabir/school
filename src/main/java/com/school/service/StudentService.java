/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.service;

import com.school.controller.exception.CourseEnrolledException;
import com.school.controller.exception.NoRegistrationFoundException;
import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.StudentCourseKey;
import com.school.entity.StudentEntity;
import com.school.repo.CourseRepository;
import com.school.repo.StudentCourseRepository;
import com.school.repo.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alamgir
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public void addStudent(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    public boolean enrollCourse(Long studentId, Long courseId) throws Exception {

        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new NoRegistrationFoundException("Student not registered");
        }

        Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
        if (!courseOptional.isPresent()) {
            throw new NoRegistrationFoundException("Course not registered");
        }
        StudentEntity student = studentOptional.get();
        CourseEntity course = courseOptional.get();

        StudentCourseEntity studentCourseEntity = studentCourseRepository.getByStudentAndCourse(student, course);
        if (studentCourseEntity != null) {
            throw new CourseEnrolledException("Alread enrolled");
        }
        if (studentCourseRepository.getNoOfEnrolledCourse(student) >= 5) {
            throw new CourseEnrolledException("Exceed enrolled course");
        }
        if (studentCourseRepository.getNoOfEnrolledStudent(course) >= 50) {
            throw new CourseEnrolledException("Exceed enrolled student");
        }

        StudentCourseEntity studentCourseEntity1 = new StudentCourseEntity();
        StudentCourseKey studentCourseKey = new StudentCourseKey();
        studentCourseKey.setCourseId(courseId);
        studentCourseKey.setStudentId(studentId);
        studentCourseEntity1.setId(studentCourseKey);
        studentCourseEntity1.setCourse(course);
        studentCourseEntity1.setStudent(student);
        studentCourseRepository.save(studentCourseEntity1);
        return true;
    }

    public List<CourseEntity> getEnrolledCourses(Long studentId) throws Exception {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new NoRegistrationFoundException("Student not registered");
        }

        StudentEntity student = studentOptional.get();

        List<StudentCourseEntity> studentCourses = studentCourseRepository.getEnrolledCourse(student);
        List<CourseEntity> courses = new ArrayList<>();
        for (StudentCourseEntity studentCourse : studentCourses) {
            courses.add(studentCourse.getCourse());
        }
        return courses;
    }
}
