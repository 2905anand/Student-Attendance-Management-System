package com.sams.attendancesystem.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @JsonBackReference
    private Branch branch;


    @Column
    private Integer semester;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    // @JoinTable(name = "TEACHER_SUBJECT_MAPPING", joinColumns = @JoinColumn(name = "teacherId"),
    // inverseJoinColumns = @JoinColumn(name = "subjectCode"))
    // @JsonManagedReference
    // private Set<Subject> subjects;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "STUDENT_SUBJECT_MAPPING", joinColumns = @JoinColumn(name = "studentId"),
    inverseJoinColumns = @JoinColumn(name = "subjectCode"))
    @JsonManagedReference
    @JsonIgnore
    private Set<Subject> subjects;

    public Student(){
    }
    
    public Student(String studentId, Integer studentRollNo, String studentName, Integer batch, String branchId,
             Integer semester) {
        this.studentId = studentId;
        this.studentRollNo = studentRollNo;
        this.studentName = studentName;
        this.batch = batch;
        this.branchId = branchId;
        // this.branch = branch;
        this.semester = semester;
        // this.subjects = subjects;
    }




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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public void setsemester(Integer semester) {
        this.semester = semester;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    

}
