package com.zpo.studentsystem.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;

@Embeddable
@Data
public class GradeId implements java.io.Serializable {
    private Long studentId;
    private Long courseId;

    public GradeId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public GradeId() {}
}
