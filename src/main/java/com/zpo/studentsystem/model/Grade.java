package com.zpo.studentsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name="grades")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    @EmbeddedId
    private GradeId id;

    @ManyToOne/*(cascade = CascadeType.)*/
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne/*(cascade = CascadeType.)*/
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Long points;
    private Long maxPoints;
}
