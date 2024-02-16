package com.sams.attendancesystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String studentId;

    @Column
    private Integer studentRollNo;

    @Column
    private String studentName;

    @Column
    private Integer batch;

    @Column
    private String branchId;

    @ManyToOne
    @JoinColumn(name = "branchId",insertable=false, updatable=false)
    private Branch branch;

    public Student(){

    }

    @Column
    private Integer semester;

    public String getstudentId() {
        return studentId;
    }

    public void setstudentId(String studentId2) {
        this.studentId = studentId2;
    }

    public Integer getstudentRollNo() {
        return studentRollNo;
    }

    public void setstudentRollNo(Integer studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getstudentName() {
        return studentName;
    }

    public void setstudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getbatch() {
        return batch;
    }

    public void setbatch(Integer batch) {
        this.batch = batch;
    }

    public String getbranchId() {
        return branchId;
    }

    public void setbranchId(String branchId) {
        this.branchId = branchId;
    }

    public Integer getsemester() {
        return semester;
    }

    public void setsemester(Integer semester) {
        this.semester = semester;
    }

}
