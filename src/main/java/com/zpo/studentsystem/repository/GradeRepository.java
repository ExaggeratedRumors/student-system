package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
}
