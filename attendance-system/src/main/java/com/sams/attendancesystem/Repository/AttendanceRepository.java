package com.sams.attendancesystem.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sams.attendancesystem.models.Attendance;

import jakarta.transaction.Transactional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{

    @Query(value="select a.* from attendance a where a.branch_id = :branchId and a.subject_id = :subjectId and a.date =:date",nativeQuery = true)
    List<Attendance> findByBranchIdandSubjectIdandDate(@Param("branchId") String branchId,@Param("subjectId") String subjectId,@Param("date") Date date);

    @Query(value="select a.* from attendance a where a.branch_id = :branchId and a.subject_id = :subjectId and a.date >=:startdate and a.date <=:enddate",nativeQuery = true)
    List<Attendance> findByBranchIdandSubjectIdandDateRange(@Param("branchId") String branchId,@Param("subjectId") String subjectId,@Param("startdate") Date startdate,@Param("enddate") Date enddate);

    @Query(value="update attendance set attended=1 where student_id = :studentId",nativeQuery=true)
    @Modifying
    @Transactional
    void updateAttendance(@Param("studentId") String studentId);
    
}
