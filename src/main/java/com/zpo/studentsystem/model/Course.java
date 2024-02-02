package com.zpo.studentsystem.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Table(name="courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "name")
    private String name;
    @Column(name = "grade")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();
}
