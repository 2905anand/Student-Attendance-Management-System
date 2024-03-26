package com.sams.attendancesystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sams.attendancesystem.models.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
    
}
