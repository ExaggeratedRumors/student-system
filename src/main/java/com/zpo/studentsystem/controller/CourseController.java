package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.Course;
import com.zpo.studentsystem.service.CourseService;
import com.zpo.studentsystem.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
class CourseController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @RequestMapping("/courses")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getCourses();
        courses.forEach(p -> log.info("SELECTED: {}", p));
        model.addAttribute("courses", courses);
        return "courses.html";
    }

    @RequestMapping("/add_course/{name}")
    public String addCourse(@PathVariable String name) {
        courseService.addCourse(name);
        return "success.html";
    }

    @RequestMapping("/delete_course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "success.html";
    }

    @RequestMapping("/add_grade/{studentId}/{courseId}/{points}")
    public String addGrade(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable Long points) {
        courseService.addGrade(studentId, courseId, points);
        return "success.html";
    }
}
