package com.sams.attendancesystem.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch {
    
    @Id
    @Column
    public String branchId;

    @Column
    public String branchName;

    @OneToMany(mappedBy = "branch")
    @JsonManagedReference
    private List<Student> students;

    public Branch(){}

    public Branch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    
    

}
