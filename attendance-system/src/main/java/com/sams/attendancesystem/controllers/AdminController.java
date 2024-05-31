package com.sams.attendancesystem.controllers;

import java.util.List;
import java.time.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sams.attendancesystem.models.Branch;
import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Subject;
import com.sams.attendancesystem.models.Teacher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private TeacherController teacherController;

    @Autowired
    private SubjectController subjectController;

    @Autowired
    private BranchController branchController;

    @Autowired
    private StudentController studentController;

    @RequestMapping("/adminOperations")
    public ModelAndView adminOperations(){

        ModelAndView mav = new ModelAndView("adminPage");

        return mav;

    }
    @RequestMapping("/teacher")
    public ModelAndView teacherForm(){
        ModelAndView mav = new ModelAndView("teacher");

        return mav;

    }

    @RequestMapping("/student")
    public ModelAndView studentForm(){
        ModelAndView mav = new ModelAndView("addStudent");

        List<Branch> branches = branchController.getAllBranch();

        mav.addObject("branches", branches);

        int thisYear = Year.now().getValue();
        List<Integer> batches=List.of(thisYear-4,thisYear-3,thisYear-2,thisYear-1,thisYear,thisYear+1);
        mav.addObject("batches", batches);
        
        return mav;

    }

    @RequestMapping("/assignSubjectToTeacher")
    public ModelAndView assignSubjectForm(){
        ModelAndView mav = new ModelAndView("assignSubject");
        List<Teacher> teacherList = teacherController.getAll();
        List<Subject> subjectList = subjectController.getAll();
        mav.addObject("teacherList", teacherList);
        mav.addObject("subjectList", subjectList);
        return mav;

    }

     @RequestMapping("/assignSubjectToStudent")
     public ModelAndView assignSubjectStudentForm(){
         ModelAndView mav = new ModelAndView("subjectStudent");
         List<Student> studentList = studentController.getAllStudents();
         List<Subject> subjectList = subjectController.getAll();
         mav.addObject("studentList", studentList);
         mav.addObject("subjectList", subjectList);
         return mav;

     }

     @RequestMapping("/bulkofStudent")
     public ModelAndView addBulkOfStudent(){
        ModelAndView mav=new ModelAndView("bulkofStudent");
        return mav;
     }

}
