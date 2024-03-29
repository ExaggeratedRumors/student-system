package com.zpo.studentsystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a student.
 * A student can have many grades - a one-to-many relationship.
 * Upon deletion of a student, all of their grades are also deleted.
 */
@Table(name="students")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Student {
    /**
     * The unique identifier of the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index")
    private Long index;

    /**
     * The name of the student.
     */
    @Column(name = "name")
    private String name;

    /**
     * The surname of the student.
     */
    @Column(name = "surname")
    private String surname;

    /**
     * The list of grades obtained by the student.
     */
    @Column(name = "grades")
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();
}
