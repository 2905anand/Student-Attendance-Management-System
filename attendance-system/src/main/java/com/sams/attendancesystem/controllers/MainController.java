package com.sams.attendancesystem.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser;

import com.sams.attendancesystem.Repository.BranchRepository;
import com.sams.attendancesystem.Repository.SubjectRepository;
import com.sams.attendancesystem.Repository.TeacherRepository;
import com.sams.attendancesystem.config.CustomUserDetails;
import com.sams.attendancesystem.models.Attendance;
import com.sams.attendancesystem.models.AttendanceReport;
import com.sams.attendancesystem.models.Branch;
import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Subject;
import com.sams.attendancesystem.models.Teacher;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @Autowired
    BranchRepository branchRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentController studentController;

    @Autowired
    AttendanceController attendanceController;

    @RequestMapping("/login")
    public ModelAndView loginPage(){

        ModelAndView mav = new ModelAndView("login");

        return mav;

    }

    @RequestMapping("/home")
    public ModelAndView homepage(HttpServletRequest request,Authentication authentication){

        Map<String,String[]> map=request.getParameterMap();
        ModelAndView mav = new ModelAndView("home");
        List<Branch> branchList=branchRepository.findAll();
        List<Subject> subjectList=subjectRepository.findAll();
        ArrayList<String> branches=new ArrayList<String>();
        ArrayList<String> subjects=new ArrayList<String>();
        for(Branch branch:branchList){
            branches.add(branch.getBranchName());
        }
        for(Subject subject:subjectList){
            subjects.add(subject.getsubjectCode());
        }
        mav.addObject("branches", branches);
        mav.addObject("subjects", subjects);
        //mav.addObject("branchList", branchList);
        return mav;

    }

    @RequestMapping("/home/attendance")
    public ModelAndView attendance(HttpServletRequest request,Authentication authentication){

        ModelAndView mav = new ModelAndView("attendance");

        List<Attendance> attendanceList = new ArrayList<Attendance>();

        CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();

        String teacherEmail = customUserDetails.getUsername();

        Teacher teacher = teacherRepository.getTeacherbyTeacherEmail(teacherEmail);

        String teachername = teacher.getteacherName();

        String semester = request.getParameter("semester");
        String branch = request.getParameter("branch");
        String course = request.getParameter("course");
        String date = request.getParameter("date");

        Date ddate = Date.valueOf(date);

        List<Branch> branchList = branchRepository.findByBranchName(branch);

        String branchId = branchList.get(0).getBranchId();

        if(attendanceController.checkAttendanceDone(branchId, course, ddate)){
            mav.setViewName("attendanceNotification");
            mav.addObject("message", "Attendance already Completed for filled details");
            return mav;

        }

        int semesterValue = Integer.parseInt(semester.substring(0, 1));

        List<Student> studentList = studentController.getStudentListForAttendance(course,branchId,semesterValue);
        //String[] studentsId=new String[studentList.size()];
        /*for(int count=0;count<studentList.size();count++){
            /*Attendance attendance=new Attendance();
            attendance.setStudentId(student.getstudentId());
            attendance.setBranchId(branchId);
            attendance.setSubjectId(course);
            attendance.setDate(ddate);
            attendance.setAttended(0);
            attendanceList.add(attendance);
        }*/

        mav.addObject("teacher", teachername);
        mav.addObject("semester", semester);
        mav.addObject("branch", branchId);
        mav.addObject("course", course);
        mav.addObject("date", date);
        mav.addObject("studentList", studentList);
        //mav.addObject("studentsId", studentsId);
        //mav.addObject("attendanceList", attendanceList);
        

        return mav;

    }

    @RequestMapping("/home/change_password")
    public ModelAndView change_password(){

        ModelAndView mav = new ModelAndView("change_password");

        return mav;

    }

    @RequestMapping("/home/report")
    public ModelAndView report(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("report");

        List<Branch> branchList = branchRepository.findAll();
        List<Subject> subjectList  = subjectRepository.findAll();
        ArrayList<String> branches = new ArrayList<String>();
        ArrayList<String> subjects = new ArrayList<String>();

        for(Branch branch:branchList){
            branches.add(branch.getBranchId());
        }

        for(Subject subject:subjectList){
            subjects.add(subject.getsubjectName());
        }

        mav.addObject("branches", branches);
        mav.addObject("subjects", subjects);

        return mav;
    }

    @RequestMapping("/home/createReport")
    public ModelAndView createReport(HttpServletRequest request){

        ModelAndView mav = new ModelAndView("attendanceReport");

        String semester = request.getParameter("semester");
        String branch = request.getParameter("branch");
        String subjectname = request.getParameter("course");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        mav.addObject("branch", branch);
        mav.addObject("subject", subjectname);
        mav.addObject("startdate", startDate);
        mav.addObject("enddate", endDate);

        int semesterValue = Integer.parseInt(semester.substring(0, 1));
        Date start = Date.valueOf(startDate);
        Date end = Date.valueOf(endDate);
        List<Subject> subject = subjectRepository.findBySubjectName(subjectname);
        String subjectCode = subject.get(0).getsubjectCode();

        List<Attendance> attendanceList=attendanceController.getCustomAttendance(branch, subjectCode, start, end);

        List<AttendanceReport> reportList = new ArrayList<AttendanceReport>();
        Set<Integer> idSet = new HashSet<Integer>();
        for(Attendance attendance : attendanceList){
            if(!idSet.contains(Integer.parseInt(attendance.getStudentId()))){
                idSet.add(Integer.parseInt(attendance.getStudentId()));
                AttendanceReport report=new AttendanceReport();
                report.setStudentId(Integer.parseInt(attendance.getStudentId()));
                Student student = studentController.getStudentbyId(attendance.getStudentId());
                report.setStudentRollNo(student.getstudentRollNo());
                report.setStudentName(student.getstudentName());
                report.setTotalClass(1);
                if(attendance.getAttended()==1){
                    report.setAttendedClass(1);
                    report.setAttendancePercentage(100);
                }
                else{
                    report.setAttendedClass(0);
                    report.setAttendancePercentage(0);
                }
                reportList.add(report);
            }
            else{
                int count=0;
                while(count<reportList.size()){
                    if(reportList.get(count).getStudentId()==Integer.parseInt(attendance.getStudentId())){
                        reportList.get(count).setTotalClass(reportList.get(count).getTotalClass()+1);
                        if(attendance.getAttended()==1){
                            reportList.get(count).setAttendedClass(reportList.get(count).getAttendedClass()+1);
                        }
                        int attended=reportList.get(count).getAttendedClass();
                        int totalClass=reportList.get(count).getTotalClass();
                        double percentage=((double)attended/(double)totalClass)*100;
                        reportList.get(count).setAttendancePercentage(percentage);
                        break;
                    }
                    count++;

                }

            }
        }

        mav.addObject("reportList", reportList);

        return mav;
    }

}
