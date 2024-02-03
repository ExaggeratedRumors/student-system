package com.zpo.studentsystem.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;

@Embeddable
@Data
public class GradeId implements java.io.Serializable {
    private Long index;
    private Long courseId;

    public GradeId(Long index, Long courseId) {
        this.index = index;
        this.courseId = courseId;
    }

    public GradeId() {}
}
