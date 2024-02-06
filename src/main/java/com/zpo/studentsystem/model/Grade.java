package com.zpo.studentsystem.model;

import com.zpo.studentsystem.config.Utils;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Map;
import com.zpo.studentsystem.config.Utils;

/**
 * Represents a grade of a student in a course.
 * A grade is a many-to-many relationship between a student and a course, as a student can have many grades and a course can have many grades.
 * A grade is also a composite primary key, as it cannot exist without a student or a course.
 */
@Table(name="grades")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Grade implements Serializable {
    @EmbeddedId
    public GradeId id;

    @ManyToOne
    @MapsId("index")
    @JoinColumn(name = "index")
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Long points;
    private Long maxPoints;
    private Double finalGrade;

    public Grade(GradeId gradeId, Student student, Course course, Long maxPoints) {
        this.id = gradeId;
        this.student = student;
        this.course = course;
        this.points = 0L;
        this.maxPoints = maxPoints;
        this.finalGrade = null;
    }

    /**
     * Calculates the percentage of the points obtained by the student in the course.
     * @return the percentage of the points obtained by the student in the course.
     */
    public double getPercentage() {
        return ((double) points / (double) maxPoints);
    }

    /**
     * Calculates the grade based on points and maximum points value.
     * @return one of grade loaded from configuration file.
     */
    public Double calculateFinalGrade() {
        double percentage = getPercentage();
        for (Map.Entry<Double, Double> entry : Utils.getGrades().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + " " + percentage);
            if (entry.getKey() <= percentage) {
                return finalGrade;
            }
        }
        return null;
    }
}
