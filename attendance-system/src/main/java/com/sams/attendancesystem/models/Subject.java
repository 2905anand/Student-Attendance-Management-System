package com.sams.attendancesystem.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;

// import org.springframework.lang.NonNull;

// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
    
    @Id
    @Column
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String subjectCode;


    // @Column
    // private String subjectCode;

    @Column
    private String subjectName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "TEACHER_SUBJECT_MAPPING", joinColumns = @JoinColumn(name = "subjectCode"),
    inverseJoinColumns = @JoinColumn(name = "teacherId"))
    @JsonBackReference
    @JsonIgnore
    private Set<Teacher> teachers;
    // private List<Teacher> teachers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "STUDENT_SUBJECT_MAPPING", joinColumns = @JoinColumn(name = "subjectCode"),
    inverseJoinColumns = @JoinColumn(name = "studentId"))
    @JsonBackReference
    @JsonIgnore
    private Set<Student> students;

    public Subject(){}

    public Subject(String subjectCode, String subjectName){
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }


    public String getsubjectCode() {
        return subjectCode;
    }

    public void setsubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public String getsubjectName() {
        return subjectName;
    }

    public void setsubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    

}
