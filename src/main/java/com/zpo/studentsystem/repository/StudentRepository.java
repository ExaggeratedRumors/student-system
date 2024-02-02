package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT u FROM Student u WHERE UPPER(u.name) LIKE ?1%") // case insensitive
    List<Student> findByFirstnameStartingWithIgnoreCase(String firstname, Sort sort);

    @Query("SELECT COUNT(u) FROM Student u WHERE u.name = ?1")
    Integer countByFirstname(String firstname);

    @Query("SELECT u FROM Grade u WHERE u.student.studentId = ?1")
    List<Grade> findByStudentId(Long studentId);
}
