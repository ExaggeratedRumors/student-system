package com.zpo.studentsystem.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Composite primary key for the Grade class.
 * A grade cannot exist without a student or a course.
 */
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
