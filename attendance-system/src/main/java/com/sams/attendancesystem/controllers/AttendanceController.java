package com.sams.attendancesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sams.attendancesystem.Repository.AttendanceRepository;
import com.sams.attendancesystem.models.Attendance;
// import com.sams.attendancesystem.models.Student;

@RestController
@RequestMapping(path = "/attendance", method = {RequestMethod.DELETE, RequestMethod.GET,RequestMethod.PUT})
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping(path = "/add")
    public String addAttendance(@RequestBody Attendance entity){
        Attendance attendance = new Attendance(entity.getattendanceId(), entity.getStudentId(), entity.getBranchId(), entity.getSubjectId(), entity.getAttended(), entity.getDate());
        attendanceRepository.save(attendance);
        return "Attendance Saved";
    }
    
}
