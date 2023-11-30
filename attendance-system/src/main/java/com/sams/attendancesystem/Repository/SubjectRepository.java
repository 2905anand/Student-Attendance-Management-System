package com.sams.attendancesystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.attendancesystem.models.Subject;




public interface SubjectRepository extends JpaRepository<Subject, Integer>{
    List<Subject> findBySubjectName(String subjectName);
    
}
