package com.sams.attendancesystem.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sams.attendancesystem.Repository.StudentRepository;
import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.Repository.TeacherRepository;
import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Subject;
import com.sams.attendancesystem.models.Teacher;

@RestController
@RequestMapping(path="/subject",method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class SubjectController {
    
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired StudentRepository studentRepository;

    @PostMapping(path = "/add")
    @JsonIgnoreProperties
    public String addNewSubject(@RequestBody Subject entity){
        Subject subject=new Subject(entity.getsubjectCode(),entity.getsubjectName());
        subjectRepository.save(subject);
        return "Saved";
    }

    @PostMapping(path = "/assignSubjectToTeacher/{teacherId}")
    public String assignTeacherToSubject(@RequestBody Subject entity, @PathVariable(name = "teacherId") String teacherId){
      System.out.println("\n Create a new subject and assign to an existing teacher\n");

      Subject subject = new Subject(entity.getsubjectCode(),entity.getsubjectName());

      subject = this.subjectRepository.save(subject);
      System.out.println("\nSaved Teacher :"+subject+"\n");

      Teacher teacher = this.teacherRepository.getReferenceById(teacherId);
      System.out.println("\n Subject Details: "+teacher.toString()+"\n");

      Set<Subject> subjects = new HashSet<>();
      subjects.add(subject);

      teacher.setSubjects(subjects);

      teacher = teacherRepository.save(teacher);

      System.out.println("Teacher assigned to subject\n");

      return "Employee Saved!!!";

    }

    @PostMapping(path = "/assignSubjectToStudent/{subjectCode}/{studentId}")
    public String assignSubjectsToStudent(@PathVariable(name = "subjectCode") String subjectCode, @PathVariable(name = "studentId") String studentId) {
        Subject subject = this.subjectRepository.getReferenceById(subjectCode);
        
        Student student = this.studentRepository.getReferenceById(studentId);

        Set<Subject> subjects = student.getSubjects();
        subjects.add(subject);

        // Set<Student> students = subject.getStudents();
        // students.add(student);

        student.setSubjects(subjects);

        student = studentRepository.save(student);

        return "Subject Assigned";

    }
    // To Retrieve All Subjects
    @GetMapping(path = "/all")
    public List<Subject> getAll(){
        return subjectRepository.findAll();

    }

    // To retrieve subject using it's name
    @GetMapping(path = "/{subjectName}")
    public List<Subject> getSubject(@PathVariable String subjectName){
        List<Subject> subjects=subjectRepository.findBySubjectName(subjectName);
        return subjects;
    }

    // to delete any Subject using its id
    @DeleteMapping(path = "/delete/{subjectCode}")
    public String delete(@PathVariable String subjectCode){
        subjectRepository.deleteById(subjectCode);
        return "Subject Deleted";
    }
}
