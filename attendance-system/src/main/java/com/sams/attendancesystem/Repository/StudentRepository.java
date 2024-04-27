package com.sams.attendancesystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sams.attendancesystem.models.Student;

public interface StudentRepository extends JpaRepository<Student, String>{
    List<Student> findByStudentName(String studentName);
    
    List<Student> findByStudentRollNo(Integer studentRollNo);

    List<Student> findByStudentId(String studentId);

    @Query(value="select s.* from student s,student_subject_mapping m where s.student_id=m.student_id and m.subject_code = :subject_code and s.branch_id = :branchId and s.semester = :semester",nativeQuery=true)
    List<Student> findStudentforAttendance(@Param("subject_code") String subject_code,@Param("branchId") String branchId,@Param("semester") Integer semester); 

}
