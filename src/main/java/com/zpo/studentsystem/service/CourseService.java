package com.zpo.studentsystem.service;

import com.zpo.studentsystem.model.Course;
import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.GradeId;
import com.zpo.studentsystem.model.Student;
import com.zpo.studentsystem.repository.CourseRepository;
import com.zpo.studentsystem.repository.GradeRepository;
import com.zpo.studentsystem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    public List<Course> getCourses() {
        return courseRepo.findAll();
    }


    public void addCourse(String name) {
        Course course = new Course();
        course.setName(name);
        courseRepo.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public void addGrade(Long studentId, Long courseId, Long points) {
        System.out.println("Adding grade for student: " + studentId + " and course: " + courseId + " with points: " + points);
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + studentId));
        System.out.println(student);
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        System.out.println(course);
        Grade grade = new Grade();
        GradeId id = new GradeId();
        id.setStudentId(studentId);
        id.setCourseId(courseId);
        grade.setId(id);
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setMaxPoints(10L);
        grade.setPoints(points);
        gradeRepo.save(grade);
    }

    public Course getCourse(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
    }
}
