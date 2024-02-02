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
    @EmbeddedId
    private GradeId id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private int points;
    private int maxPoints;
}
