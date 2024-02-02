package com.zpo.studentsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "name")
    private String name;
    @Column(name = "grade_id")
    private Long gradesId;
}
