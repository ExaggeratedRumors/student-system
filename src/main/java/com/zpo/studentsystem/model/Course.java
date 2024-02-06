package com.zpo.studentsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;


/**
 * Entity class representing a course.
 * A course can have many grades - a one-to-many relationship.
 */
@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Course {
    /**
     * The unique identifier of the course.
     */
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long courseId;

    /**
     * The name of the course.
     */
    @Column(name = "name")
    private String name;

    /**
     * The list of grades obtained by the students in the course.
     */
    @Column(name = "grade")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();
}
