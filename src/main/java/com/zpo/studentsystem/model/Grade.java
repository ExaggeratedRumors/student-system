package com.zpo.studentsystem.model;

import com.zpo.studentsystem.config.Utils;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Map;

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
    /**
     * Composite primary key for the Grade class.
     * A grade cannot exist without a student or a course.
     */
    @EmbeddedId
    public GradeId id;

    /**
     * The student who received the grade.
     */
    @ManyToOne
    @MapsId("index")
    @JoinColumn(name = "index")
    private Student student;

    /**
     * The course for which the grade was given.
     */
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * The number of points obtained by the student in the course.
     */
    @Column(name = "points")
    private Long points;

    /**
     * The maximum number of points that can be obtained in the course.
     */
    @Column(name = "max_points")
    private Long maxPoints;

    /**
     * The final grade obtained by the student in the course. Is null if the grade has not been processed yet.
     */
    @Column(name = "final_grade")
    private Double finalGrade;

    /**
     * Constructor for the Grade class.
     * @param gradeId Composite primary key for the Grade class.
     * @param student The student who received the grade.
     * @param course The course for which the grade was given.
     * @param maxPoints The maximum number of points that can be obtained in the course.
     */
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
            if (entry.getKey() <= percentage) {
                this.finalGrade = entry.getValue();
                return finalGrade;
            }
        }
        return null;
    }
}
