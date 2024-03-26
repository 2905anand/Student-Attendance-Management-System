package com.sams.attendancesystem.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attendanceId;


    @Column
    private String studentId;

    @Column
    private String branchId;

    @Column
    private String subjectId;

    @Column
    private Integer attended;

    @Column
    private Date date;

    public Attendance(Long attendanceId, String studentId, String branchId, String subjectId, Integer attended, Date date){
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.branchId = branchId;
        this.subjectId = subjectId;
        this.attended = attended;
        this.date = date;
    }

    public Long getattendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getAttended() {
        return attended;
    }

    public void setAttended(Integer attended) {
        this.attended = attended;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    
}
