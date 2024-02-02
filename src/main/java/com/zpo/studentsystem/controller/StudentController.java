package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.service.CourseService;
import com.zpo.studentsystem.model.Student;
import com.zpo.studentsystem.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
class StudentController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService service;

    @RequestMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = service.getStudents();
        students.forEach(p -> log.info("SELECTED: {}", p));
        model.addAttribute("students", students);
        return "index.html";
    }
    @RequestMapping("/delete_student/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "success.html";
    }

    @RequestMapping("/add_student/{name}/{surname}")
    public String addStudent(@PathVariable String name, @PathVariable String surname) {
        service.addStudent(name, surname);
        return "success.html";
    }


    @RequestMapping("/fill")
    public String fillStudentList() {
        service.fillStudentList();
        return "success.html";
    }

    @RequestMapping("/grades/{studentId}")
    public String getGrades(@PathVariable Long studentId, Model model) {
        List<Grade> grades = service.getGrades(studentId);
        System.out.println("" + grades.size());
        grades.forEach(p -> log.info("SELECTED: {}", p));
        model.addAttribute("grades", grades);
        return "grades.html";
    }
}
