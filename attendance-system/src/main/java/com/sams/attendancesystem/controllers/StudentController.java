package com.sams.attendancesystem.controllers;

import java.util.ArrayList;
// import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sams.attendancesystem.Repository.StudentRepository;
import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Subject;

@RestController
@RequestMapping(path = "/student", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // Function to add New Students @RequestParam Integer studentId
    @PostMapping(path = "/add")
    public String addNewStudent(@RequestBody Student entity){
        Student student = new Student(entity.getstudentId(), entity.getstudentRollNo(), entity.getstudentName(), entity.getbatch(), entity.getbranchId(), entity.getsemester());
        studentRepository.save(student);
        return "Student Saved";
    }

    @PostMapping(path = "/assignStudentToSubject/{studentId}/{subjectCode}")
    public String assignSubjectsToStudent(@PathVariable(name = "studentId") String studentId, @PathVariable(name = "subjectCode") String subjectCode) {
        Student student = this.studentRepository.getReferenceById(studentId);

        Subject subject = this.subjectRepository.getReferenceById(subjectCode);

        Set<Student> students = subject.getStudents();
        students.add(student);

        // Set<Subject> subjects = student.getSubjects();
        // subjects.add(subject);

        subject.setStudents(students);

        subject = subjectRepository.save(subject);

        return "Subject Assigned";

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

    public Student getStudentbyId(@PathVariable String studentId){
        List<Student> students = studentRepository.findByStudentId(studentId);
        if(students!=null){
            return students.get(0);
        }
        return null;
    }


    // Function to delete any Student using its ID
    @DeleteMapping(path="delete/{studentId}")
    public String  deleteStudent(@PathVariable String studentId){
        studentRepository.deleteById(studentId);
        return "Student Deleted";
    }

    public List<Student> getStudentListForAttendance(String course,String branch,int semesterValue) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getStudentListForAttendance'");
        List<Student> studentList=studentRepository.findStudentforAttendance(course, branch, semesterValue);
        return studentList;
    }
}
