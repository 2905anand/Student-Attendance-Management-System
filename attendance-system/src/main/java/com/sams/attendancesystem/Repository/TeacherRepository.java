package com.sams.attendancesystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sams.attendancesystem.models.Teacher;
// import java.util.List;




public interface TeacherRepository extends JpaRepository<Teacher, String>{
    
    @Query("select t from Teacher t where t.teacher_email = :teacher_email")
    public Teacher getTeacherbyTeacherEmail(@Param("teacher_email") String teacher_email);

}
