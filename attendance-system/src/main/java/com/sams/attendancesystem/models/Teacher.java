package com.sams.attendancesystem.models;

// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    
    @Id 
    @Column
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teacherId;

    @Column
    private String teacherName;

    public Integer getteacherId() {
        return teacherId;
    }

    public void setteacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getteacherName() {
        return teacherName;
    }

    public void setteacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
}
