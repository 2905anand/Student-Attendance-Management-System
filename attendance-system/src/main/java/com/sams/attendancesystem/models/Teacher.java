package com.sams.attendancesystem.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    
    @Id 
    @Column
    // @GeneratedValue(strategy = GenerationType.AUTO)
    public String teacherId;

    @Column
    public String teacherName;

    @Column
    private String password;

    @Column
    public String teacher_email;

    @Column
    private String teacher_role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "TEACHER_SUBJECT_MAPPING", joinColumns = @JoinColumn(name = "teacherId"),
    inverseJoinColumns = @JoinColumn(name = "subjectCode"))
    @JsonManagedReference
    @JsonIgnore
    private Set<Subject> subjects;
    // private List<Subject> subjects;

    public Teacher(){

    }

    public Teacher(String teacherId, String teacherName){
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public String getteacherId() {
        return teacherId;
    }

    public void setteacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getteacherName() {
        return teacherName;
    }

    public void setteacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public void setTeacher_email(String teacher_email) {
        this.teacher_email = teacher_email;
    }

    public String getTeacher_role() {
        return teacher_role;
    }

    public void setTeacher_role(String teacher_role) {
        this.teacher_role = teacher_role;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
    
    @Override
    public String toString() {
        return "Teacher [teacherId=" + teacherId + ", taecherName=" + teacherName +  "]";
    }
}
