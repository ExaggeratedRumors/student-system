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
        Course result = courseService.addCourse(name);
        if(result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<Long> deleteCourse(@PathVariable Long id) {
        Boolean result = courseService.deleteCourse(id);
        if(result) return new ResponseEntity<>(id, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/courses/grades/add/{studentId}/{courseId}/{maxPoints}")
    public ResponseEntity<Grade> addGrade(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable Long maxPoints) {
        Grade result = gradeService.addGrade(studentId, courseId, maxPoints);
        if(result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/courses/grades/update/{studentId}/{courseId}/{points}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable Long points) {
        Grade result = gradeService.updateGrade(studentId, courseId, points);
        if(result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/courses/average")
    public String getCourseAverageGrade(Model model) {
        HashMap<Course, Double> courses = courseService.getAverageGrade();
        model.addAttribute("courses", courses.keySet());
        model.addAttribute("grades", courses.values());
        return "courses.html";
    }

   //@RequestMapping("/average_grades")
   //public String getCourseAverageGrades(Model model) {
   //    List<Course> courses = courseService.getCourses();
   //    courses.forEach(p -> log.info("SELECTED: {}", p));
   //    model.addAttribute("courses", courses);

   //


   //    return "course_grades.html";
   //}


    /*@RequestMapping("/add_student_to_course/{studentId}/{courseId}")
    public String addStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        courseService.addStudentToCourse(studentId, courseId);
        return "success.html";
    }TODO*/
}
