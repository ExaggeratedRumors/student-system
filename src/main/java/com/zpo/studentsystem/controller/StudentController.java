package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.model.Course;
import com.zpo.studentsystem.model.Grade;
import com.zpo.studentsystem.model.Student;
import com.zpo.studentsystem.service.CourseService;
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

    @Autowired
    CourseService courseService;

    @RequestMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students.html";
    }

    @PostMapping("/students/add/{name}/{surname}")
    public ResponseEntity<Student> addStudent(@PathVariable String name, @PathVariable String surname) {
        System.out.println("Adding student \"" + name + " " + surname + "\"");
        Student result = studentService.addStudent(name, surname);
        if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        System.out.println("Student added with id " + result.getIndex());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/students/delete/{index}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long index) {
        System.out.println("Deleting student with id " + index);
        Boolean result = studentService.deleteStudent(index);
        if(!result) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        System.out.println("Student deleted");
        return new ResponseEntity<>(index, HttpStatus.OK);
    }

    @RequestMapping("/students/grades/{index}")
    public String getGrades(@PathVariable Long index, Model model) {
        List<Grade> grades = gradeService.getStudentsGrades(index);
        grades.forEach(grade -> {
            System.out.println("test " + grade.getFinalGrade() + " " + grade.getPoints() + " " + grade.getMaxPoints());
        });
        List<Course> courses = courseService.getCourses();
        model.addAttribute("grades", grades);
        model.addAttribute("courses", courses);
        model.addAttribute("index", index);
        return "grades.html";
    }
}
