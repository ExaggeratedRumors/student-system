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
    /**
     * The index of the student who received the grade.
     */
    private Long index;

    /**
     * The course for which the grade was given.
     */
    private Long courseId;

    /**
     * Constructor for the GradeId class.
     * @param index The index of the student who received the grade.
     * @param courseId The course for which the grade was given.
     */
    public GradeId(Long index, Long courseId) {
        this.index = index;
        this.courseId = courseId;
    }

    /**
     * Default constructor for the GradeId class.
     */
    public GradeId() {}
}
