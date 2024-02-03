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
    public GradeId id;

    @ManyToOne/*(cascade = CascadeType.)*/
    @MapsId("index")
    @JoinColumn(name = "index")
    private Student student;
    @ManyToOne/*(cascade = CascadeType.)*/
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Long points;
    private Long maxPoints;

    public double getPercentage() {
        return ((double) points / (double) maxPoints) * 100;
    }
}
