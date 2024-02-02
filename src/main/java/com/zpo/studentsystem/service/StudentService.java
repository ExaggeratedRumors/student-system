package com.zpo.studentsystem.service;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.repository.StudentRepository;
import com.zpo.studentsystem.model.Student;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentRepository gradeRepo;

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public void fillStudentList() {
        addStudent("Lorem", "Ipsum");
        addStudent("Zagrożenie", "Z Fizyki");
        addStudent("Chat", "GPT");
        addStudent("ŚĆŹŻ", "1234");
    }

    public void deleteStudent(Long id) {
        getStudent(id);
        studentRepo.deleteById(id);
    }

    public void addStudent(String name, String surname) {

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        studentRepo.save(student);
    }

    public List<Grade> getGrades(Long studentId) {
        return gradeRepo.findByStudentId(studentId);
    }

    public Student getStudent(Long studentId) {
        return studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found with id " + studentId));
    }
}
