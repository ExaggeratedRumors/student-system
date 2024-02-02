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
        return gradeRepo.findByStudentId(studentId);
    }

    public Grade addGrade(Long studentId, Long courseId, Long maxPoints) {
        if(maxPoints < 1) return null;
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
        if(student == null || course == null) return null;
        Grade grade = new Grade( new GradeId(studentId, courseId), student, course, 0L, maxPoints);
        return gradeRepo.save(grade);
    }

    public Integer updateGrade(Long studentId, Long courseId, Long points) {
        if(points < 0) return -1;
        Long maxPoints = gradeRepo.findById(new GradeId(studentId, courseId)).orElseThrow(() -> new EntityNotFoundException("Grade not found with studentId " + studentId + " and courseId " + courseId)).getMaxPoints();
        if(points > maxPoints) return -1;
        return gradeRepo.updateGrade(studentId, courseId, points);
    }
}
