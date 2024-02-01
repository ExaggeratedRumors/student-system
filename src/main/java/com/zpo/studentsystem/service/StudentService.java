package com.zpo.studentsystem.service;

import com.zpo.studentsystem.repository.StudentRepository;
import com.zpo.studentsystem.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public void fillStudentList() {
        repository.save(new Student(1L, "Lorem", "Ipsum", 1L));
        repository.save(new Student(2L, "Zagrożenie", "Z Fizyki", 1L));
        repository.save(new Student(3L, "Chat", "GPT", 1L));
        repository.save(new Student(4L, "ŚĆŹŻ", "1234", 1L));
    }
}
