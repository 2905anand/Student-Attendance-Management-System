package com.sams.attendancesystem.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @Column
    private String studentId;

    @Column 
    private String studentName;

    @Column
    private String branchId;

    @Column
    private String subjectId;

    @Column
    private Integer attended;

    @Column 
    private Integer total;

    @Column
    private Date date;

    

    
}
