package com.zpo.studentsystem.repository;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

/**
 * Repository for the Grade entity.
 * Contains methods for finding and updating grades directly in the database.
 */
public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    /**
     * Method for finding all grades by student index.
     * @param index Index of the student.
     * @return List of all grades of the student.
     */
    @Query("SELECT u FROM Grade u WHERE u.student.index = ?1")
    List<Grade> findByIndex(Long index);


    /**
     * Method for updating the points of a grade.
     * @param index Index of the student.
     * @param courseId Id of the course.
     * @param points New points of the grade.
     * @return Number of updated grades. (Should be 1 if successful)
     */
    @Modifying
    @Query("UPDATE Grade SET points = ?3 WHERE id.index = ?1 AND id.courseId = ?2")
    Integer updatePoints(Long index, Long courseId, Long points);

    /**
     * Method for updating the final grade.
     * @param index Index of the student.
     * @param courseId Id of the course.
     * @param finalGrade New grade.
     * @return Number of updated grades. (Should be 1 if successful)
     */
    @Modifying
    @Query("UPDATE Grade SET finalGrade = ?3 WHERE id.index = ?1 AND id.courseId = ?2")
    Integer updateFinalGrade(Long index, Long courseId, Double finalGrade);
}
