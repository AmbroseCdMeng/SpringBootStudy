package org.sang.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtils {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * 加密 BCrypt
     * @return
     */
    public static String EncryptBCrypt(String s){
        System.out.println(passwordEncoder.encode(s));
        return  passwordEncoder.encode(s);
    }
}