package com.sams.attendancesystem.models;

import java.util.List;

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
    private String branchId;

    @Column
    private String branchName;

    @OneToMany(mappedBy = "branch")
    private List<Student> students;

    // public Branch(){

    // }

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

    

}
