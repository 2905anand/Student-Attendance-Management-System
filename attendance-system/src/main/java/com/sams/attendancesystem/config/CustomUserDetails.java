package com.sams.attendancesystem.config;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.sams.attendancesystem.models.Teacher;

public class CustomUserDetails implements UserDetails {

    private Teacher teacher;

    public CustomUserDetails(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(teacher.getTeacher_role());
        return List.of(simpleGrantedAuthority);
        //throw new UnsupportedOperationExceptuserion("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        return teacher.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        return teacher.getTeacher_email();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }



}
