package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
