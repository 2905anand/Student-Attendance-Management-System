package com.sams.attendancesystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sams.attendancesystem.Repository.StudentRepository;
import com.sams.attendancesystem.models.Student;

@RestController
@RequestMapping(path = "/student", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT})
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    // Function to add New Students @RequestParam Integer studentId
    @PostMapping(path = "/add")
    public String addNewStudent(@RequestParam Integer studentRollNo, @RequestParam String studentName, @RequestParam Integer batch, @RequestParam String branchId, @RequestParam Integer semester){
        Student n=new Student();
        // n.setstudentId(studentId);
        n.setstudentRollNo(studentRollNo);
        n.setstudentName(studentName);
        n.setbatch(batch);
        n.setbranchId(branchId);
        n.setsemester(semester);
        studentRepository.save(n);
        return "Saved";
    }

    // Function to Retrieve All Subjects
    @GetMapping(path = "/all")
    public Iterable<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // Find Student by name
    @GetMapping(path = "/name/{studentName}")
    public List<Student> getStudent(@PathVariable String studentName){
        List<Student> students = studentRepository.findByStudentName(studentName);
        return students;
    }

    // Find Student by Roll No.
    @GetMapping(path = "/rollNo/{studentRollNo}")
    public List<Student> getStudentbyRoll(@PathVariable Integer studentRollNo){
        List<Student> students = studentRepository.findByStudentRollNo(studentRollNo);
        return students;
    }


    // Function to delete any Student using its ID
    @DeleteMapping(path="delete/{studentId}")
    public String  deleteStudent(@PathVariable Integer studentId){
        studentRepository.deleteById(studentId);
        return "Student Deleted";
    }
}
