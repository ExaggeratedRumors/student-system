package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    @Query("SELECT u FROM Grade u WHERE u.student.studentId = ?1")
    List<Grade> findByStudentId(Long studentId);
}
