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

    public void addGrade(Long studentId, Long courseId, Long points) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found with id " + courseId));
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
}
