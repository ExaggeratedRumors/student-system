package com.zpo.studentsystem.controller;

import com.zpo.studentsystem.config.Utils;
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

/**
 * Controller for the Student entity.
 * Contains methods for handling HTTP requests related to students.
 */
@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    CourseService courseService;

    /**
     * Request GET for page of students list.
     * @param model model of html template.
     * @return name of html file.
     */
    @RequestMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students.html";
    }

    /**
     * Request POST to add another student.
     * @param name student's name.
     * @param surname student's lastname.
     * @return student as object who has been added to data base with the response code.
     */
    @PostMapping("/students/add/{name}/{surname}")
    public ResponseEntity<Student> addStudent(@PathVariable String name, @PathVariable String surname) {
        if(Utils.DEBUG) System.out.println("Adding student \"" + name + " " + surname + "\"");
        Student result = studentService.addStudent(name, surname);
        if(result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(Utils.DEBUG) System.out.println("Student added with id " + result.getIndex());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Request DELETE to remove student from data base.
     * @param index student's index number;
     * @return response code - 404 or 200.
     */
    @DeleteMapping("/students/delete/{index}")
    public ResponseEntity<Long> deleteStudent(@PathVariable Long index) {
        if(Utils.DEBUG) System.out.println("Deleting student with id " + index);
        Boolean result = studentService.deleteStudent(index);
        if(!result) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(Utils.DEBUG) System.out.println("Student deleted");
        return new ResponseEntity<>(index, HttpStatus.OK);
    }

    /**
     * Request GET to get every student's grades.
     * @param index student's index number;
     * @return name of html file.
     */
    @RequestMapping("/students/grades/{index}")
    public String getGrades(@PathVariable Long index, Model model) {
        List<Grade> grades = gradeService.getStudentsGrades(index);
        if(Utils.DEBUG)
            grades.forEach(grade -> System.out.println("test " + grade.getFinalGrade() + " " + grade.getPoints() + " " + grade.getMaxPoints()));
        List<Course> courses = courseService.getCourses();
        model.addAttribute("grades", grades);
        model.addAttribute("courses", courses);
        model.addAttribute("index", index);
        return "grades.html";
    }
}
