package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.*;
import com.zpo.studentsystem.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.EntityResponse;
import java.util.HashMap;

import java.util.List;

@Controller
class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @RequestMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses.html";
    }

    @PostMapping("/courses/add/{name}")
    public ResponseEntity<Course> addCourse(@PathVariable String name) {
        System.out.println("Adding course \"" + name+"\"");
        Course result = courseService.addCourse(name);
        if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Course added with id " + result.getCourseId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        System.out.println("Deleting course with id " + id);
        Boolean result = courseService.deleteCourse(id);
        if(!result) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        System.out.println("Course deleted");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/courses/grades/add/{index}/{courseId}/{maxPoints}")
    public ResponseEntity<Long> addGrade(@PathVariable Long index, @PathVariable Long courseId, @PathVariable Long maxPoints) {
        System.out.println("Adding grade for student " + index + " and course " + courseId + " with max points " + maxPoints);
        Grade result = gradeService.addGrade(index, courseId, maxPoints);
        if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Grade added");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/courses/grades/update/{index}/{courseId}/{points}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long index, @PathVariable Long courseId, @PathVariable Long points) {
        System.out.println("Updating grade for student " + index + " and course " + courseId + " with points " + points);
        Integer result = gradeService.updateGrade(index, courseId, points);
        if(result <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Grade updated");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/courses/average")
    public String getCourseAverageGrade(Model model) {
        HashMap<Course, Double> courses = courseService.getAverageGrade();
        model.addAttribute("courses", courses);
        return "average_courses.html";
    }

}
