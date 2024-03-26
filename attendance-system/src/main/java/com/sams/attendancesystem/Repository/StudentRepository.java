package com.sams.attendancesystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.attendancesystem.models.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
    List<Student> findByStudentName(String studentName);
    
    List<Student> findByStudentRollNo(Integer studentRollNo);
}
