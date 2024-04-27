package com.sams.attendancesystem.models;

public class AttendanceReport {

    public int studentId;
    public int studentRollNo;
    public String studentName;
    public int totalClass;
    public int attendedClass;
    public double attendancePercentage;

    public int getStudentRollNo() {
        return studentRollNo;
    }
    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getTotalClass() {
        return totalClass;
    }
    public void setTotalClass(int totalClass) {
        this.totalClass = totalClass;
    }
    public int getAttendedClass() {
        return attendedClass;
    }
    public void setAttendedClass(int attendedClass) {
        this.attendedClass = attendedClass;
    }
    public double getAttendancePercentage() {
        return attendancePercentage;
    }
    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
}
