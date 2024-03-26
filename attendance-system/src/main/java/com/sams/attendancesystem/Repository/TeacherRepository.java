package com.sams.attendancesystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.sams.attendancesystem.models.Teacher;
// import java.util.List;



public interface TeacherRepository extends JpaRepository<Teacher, String>{
    


}
