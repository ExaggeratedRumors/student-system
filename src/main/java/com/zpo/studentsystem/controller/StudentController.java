package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.Student;
import com.zpo.studentsystem.service.GradeService;
import com.zpo.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @RequestMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students.html";
    }

    @PostMapping("/students/add/{name}/{surname}")
    public ResponseEntity<Student> addStudent(@PathVariable String name, @PathVariable String surname) {
        Student result = studentService.addStudent(name, surname);
        if(result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        Boolean result = studentService.deleteStudent(id);
        if(result) return new ResponseEntity<>(id, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/students/grades/{studentId}")
    public String getGrades(@PathVariable Long studentId, Model model) {
        List<Grade> grades = gradeService.getStudentsGrades(studentId);
        model.addAttribute("grades", grades);
        return "grades.html";
    }
}
