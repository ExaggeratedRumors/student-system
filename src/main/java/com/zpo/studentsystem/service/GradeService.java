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

import java.util.List;

/**
 * Service class for the Grade entity.
 * Contains methods for adding, updating and getting grades.
 */
@Service
@Transactional
public class GradeService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    /**
     * Method for getting all grades.
     * @return List of all grades.
     */
    public List<Grade> getGrades() {
        return gradeRepo.findAll();
    }
    /**
     * Method for getting all grades of a student.
     * @param studentId Id of the student.
     * @return List of all grades of the student.
     */
    public List<Grade> getStudentsGrades(Long studentId) {
        return gradeRepo.findByIndex(studentId);
    }

    /**
     * Method for enrollment of a student to a course.
     * @param index Index of the student.
     * @param courseId Id of the course.
     * @param maxPoints Maximum points of the grade.
     * @return The added grade (0 points)
     */
    public Grade addGrade(Long index, Long courseId, Long maxPoints) {
        if(maxPoints < 1) return null;
        Student student = studentRepo.findById(index).orElseThrow(() -> new EntityNotFoundException("Student not found with index " + index));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        if(student == null || course == null) return null;
        Grade grade = new Grade( new GradeId(index, courseId), student, course, maxPoints);
        return gradeRepo.save(grade);
    }

    /**
     * Method for updating the points of a grade.
     * @param index Index of the student.
     * @param courseId Id of the course.
     * @param points New points of the grade.
     * @return Number of updated grades. (Should be 1 if successful)
     */
    public Integer updateGrade(Long index, Long courseId, Long points) {
        if(points < 0) return -1;
        Grade grade = gradeRepo.findById(new GradeId(index, courseId)).orElseThrow(() -> new EntityNotFoundException("Grade not found with index " + index + " and courseId " + courseId));
        if(points > grade.getMaxPoints()) return -1;
        Integer result = gradeRepo.updatePoints(index, courseId, points);
        grade.setPoints(points);
        if(result == 0) return result;
        Double newGrade = grade.calculateFinalGrade();
        int res = gradeRepo.updateFinalGrade(index, courseId, newGrade);
        if(res != 1) return res;
        return result;
    }
}
