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
import java.util.Objects;

/**
 * Service class for the Course entity.
 * Contains methods for adding, deleting and getting courses.
 */
@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    /**
     * Method for getting all courses.
     * @return List of all courses.
     */
    public List<Course> getCourses() {
        return courseRepo.findAll();
    }

    /**
     * Method for adding a course.
     * @param name Name of the course.
     * @return The added course.
     */
    public Course addCourse(String name) {
        Course course = new Course();
        course.setName(name);
        return courseRepo.save(course);
    }

    /**
     * Method for deleting a course.
     * @param id Id of the course to be deleted.
     * @return True if the course was deleted, false if the course was not found.
     * @throws EntityNotFoundException If the course with the given id was not found.
     */
    public Boolean deleteCourse(Long id) {
        if(getCourse(id) == null) return false;
        courseRepo.deleteById(id);
        return true;
    }

    /**
     * Method for getting a course by its id.
     * @param courseId Id of the course.
     * @return The course with the given id.
     * @throws EntityNotFoundException If the course with the given id was not found.
     */
    public Course getCourse(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
    }

    /**
     * Method for getting an average grade for each course.
     * @return A map with courses as keys and their average grades as values.
     */
    public HashMap<Course, Double> getAverageGrade() {
        List<Course> courses = courseRepo.findAll();
        courses.removeIf(it -> it.getGrades().isEmpty());
        HashMap<Course, Double> averageGrades = new HashMap<>();
        double value;

        for(Course c : courses) {
            value = c.getGrades().stream()
                    .mapToDouble(Grade::calculateFinalGrade)
                    .filter(Objects::nonNull)
                    .average()
                    .orElse(0.0);
            averageGrades.put(c, value);
        }
        return averageGrades;
    }

}
