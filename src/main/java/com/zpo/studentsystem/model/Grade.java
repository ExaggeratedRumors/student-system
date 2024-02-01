package com.zpo.studentsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="grades")
@Entity
@Data
public class Grade {
    @Id
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "points")
    private Integer points;
    @Column(name = "max_points")
    private Integer maxPoints;
    @Column(name = "final_grade")
    private Integer finalGrade;
}
