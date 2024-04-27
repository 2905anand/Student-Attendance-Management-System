package com.sams.attendancesystem.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sams.attendancesystem.Repository.AttendanceRepository;
import com.sams.attendancesystem.Repository.StudentRepository;
import com.sams.attendancesystem.models.Attendance;
// import com.sams.attendancesystem.models.Student;
import com.sams.attendancesystem.models.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/attendance", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT})
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentController studentController;

    @PostMapping(path = "/add")
    public String addAttendance(@RequestBody Attendance entity){
        Attendance attendance = new Attendance(entity.getattendanceId(), entity.getStudentId(), entity.getBranchId(), entity.getSubjectId(), entity.getAttended(), entity.getDate());
        attendanceRepository.save(attendance);
        return "Attendance Saved";
    }

    @PostMapping(path = "/updateAttendance")
    public ModelAndView updateAttendance(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView("attendanceNotification");

        Map<String,String[]> parameterMap=request.getParameterMap();

        String branchId=parameterMap.get("branchId")[0];
        String subjectId=parameterMap.get("subjectId")[0];
        String date=parameterMap.get("date")[0];
        String semester=parameterMap.get("semester")[0];
        String[] presentStudents=parameterMap.get("presentstudents");
        //String[] absentStudents=parameterMap.get("absentstudents");
        int semesterValue = Integer.parseInt(semester.substring(0, 1));
        List<Student> studentList = studentController.getStudentListForAttendance(subjectId,branchId,semesterValue);

        Date ddate = Date.valueOf(date);

        for(Student student:studentList){
            Attendance attendance = new Attendance();
            attendance.setStudentId(student.getstudentId());
            attendance.setBranchId(branchId);
            attendance.setSubjectId(subjectId);
            attendance.setDate(ddate);
            attendance.setAttended(0);
            attendanceRepository.save(attendance);
        }
        for(String studentId : presentStudents){

            attendanceRepository.updateAttendance(studentId);
        }
        /*for(String studentId : absentStudents){

            Attendance attendance = new Attendance();
            attendance.setStudentId(studentId);
            attendance.setBranchId(branchId);
            attendance.setSubjectId(subjectId);
            attendance.setDate(ddate);
            attendance.setAttended(0);

            attendanceRepository.save(attendance);
        }*/
        
        return modelAndView;
    }

    public boolean checkAttendanceDone(String branchId,String subjectId,Date date){

        List<Attendance> attendanceList = attendanceRepository.findByBranchIdandSubjectIdandDate(branchId,subjectId,date);
        if(attendanceList.size()==0){
            return false;
        }
        return true;
    }

    public List<Attendance> getCustomAttendance(String branchId,String subjectId,Date startdate,Date enddate){

        List<Attendance> attendanceList = attendanceRepository.findByBranchIdandSubjectIdandDateRange(branchId,subjectId,startdate,enddate);
        return attendanceList;
        
    }
    
}
