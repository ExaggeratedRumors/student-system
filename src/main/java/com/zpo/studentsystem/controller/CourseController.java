package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.config.Utils;
import com.zpo.studentsystem.model.*;
import com.zpo.studentsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.List;

/**
 * Controller for the Course entity.
 * Contains methods for handling HTTP requests related to courses.
 */
@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    /**
     * Method for viewing the courses
     * @param model Model for the view
     * @return name of the html file
     */
    @RequestMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses.html";
    }

    /**
     * Method for adding a course
     * @param name Name of the course
     * @return ResponseEntity with the added course
     *        If the course was not added, returns BAD_REQUEST.
     *        If the course was added, returns OK.
     */
    @PostMapping("/courses/add/{name}")
    public ResponseEntity<Course> addCourse(@PathVariable String name) {
        if(Utils.DEBUG) System.out.println("Adding course \"" + name+"\"");
        Course result = courseService.addCourse(name);
        if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(Utils.DEBUG) System.out.println("Course added with id " + result.getCourseId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Method for deleting a course
     * @param id Id of the course
     * @return ResponseEntity with the id of the deleted course
     *        If the course was not deleted, returns NOT_FOUND.
     *        If the course was deleted, returns OK.
     */
    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        if(Utils.DEBUG) System.out.println("Deleting course with id " + id);
        Boolean result = courseService.deleteCourse(id);
        if(!result) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(Utils.DEBUG) System.out.println("Course deleted");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Method for enrolling a student to a course
     * @param index Index of the student
     * @param courseId Id of the course
     * @param maxPoints Maximum points of the grade
     * @return ResponseEntity
     *        If the student was not enrolled, returns BAD_REQUEST.
     *        If the student was enrolled, returns OK.
     */
     @PostMapping("/courses/grades/add/{index}/{courseId}/{maxPoints}")
    public ResponseEntity<Long> addGrade(@PathVariable Long index, @PathVariable Long courseId, @PathVariable Long maxPoints) {
         if(Utils.DEBUG) System.out.println("Adding grade for student " + index + " and course " + courseId + " with max points " + maxPoints);
         Grade result = gradeService.addGrade(index, courseId, maxPoints);
         if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         if(Utils.DEBUG) System.out.println("Grade added");
         return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for setting a final grade
     * @param index Index of the student
     * @param courseId Id of the course
     * @param points Points of the grade
     * @return ResponseEntity
     *        If the grade was not set returns BAD_REQUEST.
     *        If the grade was set, returns OK.
     */
    @PostMapping("/courses/grades/update/{index}/{courseId}/{points}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long index, @PathVariable Long courseId, @PathVariable Long points) {
        if(Utils.DEBUG) System.out.println("Updating grade for student " + index + " and course " + courseId + " with points " + points);
        Integer result = gradeService.updateGrade(index, courseId, points);
        if(result <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(Utils.DEBUG) System.out.println("Grade updated");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method for viewing the average grade for each course
     * @param model Model for the view
     * @return name of the html file
     */
    @RequestMapping("/courses/average")
    public String getCourseAverageGrade(Model model) {
        HashMap<Course, Double> courses = courseService.getAverageGrade();
        model.addAttribute("courses", courses);
        return "average_courses.html";
    }

}
