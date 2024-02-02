package com.zpo.studentsystem.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class GradeId implements java.io.Serializable {
    private Long studentId;
    private Long courseId;
}
