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

@Service
@Transactional
public class GradeService {

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    public List<Grade> getGrades() {
        return gradeRepo.findAll();
    }

    public List<Grade> getStudentsGrades(Long studentId) {
        return gradeRepo.findByIndex(studentId);
    }

    public Grade addGrade(Long index, Long courseId, Long maxPoints) {
        if(maxPoints < 1) return null;
        Student student = studentRepo.findById(index).orElseThrow(() -> new EntityNotFoundException("Student not found with index " + index));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        if(student == null || course == null) return null;
        Grade grade = new Grade( new GradeId(index, courseId), student, course, maxPoints);
        return gradeRepo.save(grade);
    }

    public Integer updateGrade(Long index, Long courseId, Long points) {
        if(points < 0) return -1;
        Grade grade = gradeRepo.findById(new GradeId(index, courseId)).orElseThrow(() -> new EntityNotFoundException("Grade not found with index " + index + " and courseId " + courseId));
        if(points > grade.getMaxPoints()) return -1;
        Integer result = gradeRepo.updatePoints(index, courseId, points);
        grade.setPoints(points);
        if(result == 0) return result;
        Double newGrade = grade.calculateFinalGrade();
        gradeRepo.updateFinalGrade(index, courseId, newGrade);
        return result;
    }
}
