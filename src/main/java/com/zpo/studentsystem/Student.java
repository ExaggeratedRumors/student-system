package com.zpo.studentsystem;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="students")
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String name;
    private String surname;
    private Long gradesId;

}
