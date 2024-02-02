package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.Course;
import com.zpo.studentsystem.model.Student;
import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.service.CourseService;
import com.zpo.studentsystem.service.GradeService;
import com.zpo.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class RootController {
    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;
    @RequestMapping("/")
    public String getCourses(Model model) {
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        List<Grade> grades = gradeService.getGrades();
        model.addAttribute("grades", grades);
        return "index.html";
    }
}
