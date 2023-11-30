package com.sams.attendancesystem.models;

// import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subjectId;


    @Column
    private String subjectCode;

    @Column
    private String subjectName;

    public Integer getsubjectId() {
        return subjectId;
    }

    public void setsubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

}
