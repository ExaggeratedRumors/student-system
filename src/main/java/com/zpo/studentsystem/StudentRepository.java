package com.zpo.studentsystem;

import com.zpo.studentsystem.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // No need to put query annotation if method name follows naming rule.
    // For details, check Spring Data JPA documentation
    List<Student> findByLastname(String lastname);

    @Query("SELECT u FROM Student u WHERE UPPER(u.name) LIKE ?1%") // case insensitive
    List<Student> findByFirstnameStartingWithIgnoreCase(String firstname, Sort sort);

    @Query("SELECT COUNT(u) FROM Student u WHERE u.name = ?1")
    Integer countByFirstname(String firstname);
}
