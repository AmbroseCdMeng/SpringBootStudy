package org.sang.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * 测试注解安全配置
 */

@Service
public class MethodService {

    // 访问该方法需要 ADMIN 角色。这里注意角色前需要前缀 ROLE_
    @Secured("ROLE_ADMIN")
    public String admin(){
        return "Hello Admin";
    }

    // 访问该方法需要 ADMIN 和 DBA 双角色。
    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    public String dba(){
        return "Hello DBA";
    }

    // 访问该方法需要 ADMIN 或者 DBA 或者 USER 角色
    @PreAuthorize("hasAnyRole('ADMIN', 'DBA', 'USER')")
    public String user(){
        return "Hello USER";
    }
}
