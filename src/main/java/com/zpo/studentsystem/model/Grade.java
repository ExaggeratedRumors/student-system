package com.zpo.studentsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="grades")
@NoArgsConstructor
@AllArgsConstructor
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
