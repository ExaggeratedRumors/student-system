package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.service.StudentService;
import com.zpo.studentsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
class StudentController {

    @Autowired
    StudentService service;
    @RequestMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = service.getStudents();
        model.addAttribute("students", students);
        return "index";
    }
}
