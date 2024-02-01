package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.service.StudentService;
import com.zpo.studentsystem.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/fill")
    public String fillStudentList() {
        service.fillStudentList();
        return "success.html";
    }
}
