package com.zpo.studentsystem.service;

import com.zpo.studentsystem.model.Course;
import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.repository.CourseRepository;
import com.zpo.studentsystem.repository.GradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private GradeRepository gradeRepo;

    public List<Course> getCourses() {
        return courseRepo.findAll();
    }


    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    public void addGrade(Grade grade) {
        gradeRepo.save(grade);
    }

    public Course getCourse(Long courseId) {
        return courseRepo.findById(courseId).get();
    }
}
