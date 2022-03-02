/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.service;

import com.school.controller.exception.CourseEnrolledException;
import com.school.controller.exception.NoRegistrationFoundException;
import com.school.controller.exception.ResourceNotFoundException;
import com.school.entity.CourseEntity;
import com.school.entity.StudentCourseEntity;
import com.school.entity.StudentCourseKey;
import com.school.entity.StudentEntity;
import com.school.repo.CourseRepository;
import com.school.repo.StudentCourseRepository;
import com.school.repo.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void updateStudent(Long studentId, StudentEntity studentEntity) throws ResourceNotFoundException {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        studentEntity.setId(studentId);
        studentRepository.save(studentEntity);
    }

    public void removeStudent(Long studentId) throws ResourceNotFoundException {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        studentRepository.deleteById(studentId);
    }

    public Page<StudentEntity> getStudents(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageable);
    }

    public boolean enrollCourse(Long studentId, Long courseId) throws Exception {

        StudentCourseEntity studentCourseEntity = studentCourseRepository.getByStudentAndCourse(studentId, courseId);
        if (studentCourseEntity != null) {
            throw new CourseEnrolledException("Alread enrolled");
        }

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

        if (studentCourseRepository.getNoOfEnrolledCourse(studentId) >= 5) {
            throw new CourseEnrolledException("Exceed enrolled course");
        }
        if (studentCourseRepository.getNoOfEnrolledStudent(courseId) >= 50) {
            throw new CourseEnrolledException("Exceed enrolled student");
        }

        StudentCourseEntity studentCourse = new StudentCourseEntity();
        StudentCourseKey studentCourseKey = new StudentCourseKey();
        studentCourseKey.setCourseId(courseId);
        studentCourseKey.setStudentId(studentId);
        studentCourse.setId(studentCourseKey);
        studentCourse.setCourse(course);
        studentCourse.setStudent(student);
        studentCourseRepository.save(studentCourse);
        return true;
    }

    public List<CourseEntity> getEnrolledCourses(Long studentId) throws Exception {
        List<CourseEntity> courses = studentCourseRepository.getEnrolledCourse(studentId);

        return courses;
    }

    public List<StudentEntity> getEnrolledStudents(Long courseId) throws Exception {
        List<StudentEntity> students = studentCourseRepository.getEnrolledStudent(courseId);
        return students;
    }

    public List<StudentEntity> getStudentWithNocourse() throws Exception {
        List<Long> studentIds = studentCourseRepository.getStudentsWithNoCourse();
        List<StudentEntity> students = studentRepository.findAllById(studentIds);
        return students;
    }
    public List<CourseEntity> getCourseWithNoStudent() throws Exception {
        List<Long> courseIds = studentCourseRepository.getCourseWithNoStudent();
        List<CourseEntity> courses = courseRepository.findAllById(courseIds);
        return courses;
    }
}
