package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    @Query("SELECT u FROM Grade u WHERE u.student.index = ?1")
    List<Grade> findByIndex(Long index);

    @Modifying
    @Query("UPDATE Grade SET points = ?3 WHERE id.index = ?1 AND id.courseId = ?2")
    Integer updateGrade(Long index, Long courseId, Long points);
}
