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

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(String name, String surname) {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        return studentRepo.save(student);
    }

    public Boolean deleteStudent(Long index) {
        if(getStudent(index) == null) return false;
        studentRepo.deleteById(index);
        return true;
    }

    public Student getStudent(Long index) {
        return studentRepo.findById(index).orElseThrow(
                () -> new EntityNotFoundException("Student not found with id " + index)
        );
    }
}
