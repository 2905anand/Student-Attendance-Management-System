package com.sams.attendancesystem.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncodeDecode {

    public static String passwordEncoder(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

}
