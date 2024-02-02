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
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    public Course addCourse(String name) {
        Course course = new Course();
        course.setName(name);
        return courseRepo.save(course);
    }

    public Boolean deleteCourse(Long id) {
        if(getCourse(id) == null) return false;
        courseRepo.deleteById(id);
        return true;
    }

    public Course getCourse(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
    }

    public HashMap<Course, Double> getAverageGrade() {
        List<Course> courses = courseRepo.findAll();
        courses.removeIf(it -> it.getGrades().isEmpty());
        HashMap<Course, Double> averageGrades = new HashMap<>();
        double value;

        for(Course c : courses) {
            value = c.getGrades().stream()
                    .mapToDouble(Grade::getPercentage)
                    .average()
                    .orElse(0.0);
            averageGrades.put(c, value);
        }
        return averageGrades;
    }

}
