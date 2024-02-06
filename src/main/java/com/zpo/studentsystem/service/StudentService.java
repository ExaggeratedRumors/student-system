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

/**
 * Service class for the Student entity.
 * Contains methods for adding, deleting and getting students.
 */
@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    /**
     * Method for getting all students.
     * @return List of all students.
     */
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    /**
     * Method for adding a student.
     * @param name Name of the student.
     * @param surname Surname of the student.
     * @return The added student.
     */
    public Student addStudent(String name, String surname) {
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        return studentRepo.save(student);
    }

    /**
     * Method for deleting a student.
     * @param index Index of the student to be deleted.
     * @return True if the student was deleted, false if the student was not found.
     * @throws EntityNotFoundException If the student with the given index was not found.
     */
    public Boolean deleteStudent(Long index) {
        if(getStudent(index) == null) return false;
        studentRepo.deleteById(index);
        return true;
    }

    /**
     * Method for getting a student by their index.
     * @param index Index of the student.
     * @return The student with the given index.
     * @throws EntityNotFoundException If the student with the given index was not found.
     */
    public Student getStudent(Long index) {
        return studentRepo.findById(index).orElseThrow(
                () -> new EntityNotFoundException("Student not found with id " + index)
        );
    }
}
