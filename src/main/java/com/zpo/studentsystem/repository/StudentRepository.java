package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of Student entities.
 * Contains methods for finding students directly in the database.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
