package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Repository for the Student entity.
 * Contains methods for finding students directly in the database.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
