package org.sang.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegService {
    public int reg(String username, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(password);
        // 这里将加密后的用户名和密文保存到数据库。返回保存是否成功标识。
        return 1;
    }
}
