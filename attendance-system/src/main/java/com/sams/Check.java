package com.sams;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Check {
    public static void main(String[] args){
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
        String s="1st";
        System.out.println(s.substring(0,1));
    }
}
