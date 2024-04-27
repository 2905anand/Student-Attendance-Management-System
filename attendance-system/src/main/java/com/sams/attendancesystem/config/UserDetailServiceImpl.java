package com.sams.attendancesystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sams.attendancesystem.Repository.TeacherRepository;
import com.sams.attendancesystem.models.Teacher;

@Component
public class UserDetailServiceImpl implements UserDetailsService  {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String teacher_email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Teacher teacher = teacherRepository.getTeacherbyTeacherEmail(teacher_email);

        if(teacher==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(teacher);

        return customUserDetails;
        //throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
