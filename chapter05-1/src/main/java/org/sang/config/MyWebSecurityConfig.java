package org.sang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// 自定义 MyWebSecurityConfig 继承 WebSecurityConfigurerAdapter

@Configuration
// 开启基于注解的安全配置
// prePostEnable = true 解锁 @PreAuthorize 和 @PostAuthorize 注解。分别是在方法执行前和方法执行后进行验证。
// securedEnabled = true 解锁 @Secured 注解
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 引入密码的加密方式。必须指定一种。
    @Bean
    PasswordEncoder passwordEncoder() {
        // 这里使用 NoOpPasswordEncode，即不对密码进行加密。该方法因安全性不足够已被标记为过时
        return NoOpPasswordEncoder.getInstance();

        // 也可以使用 DelegatingPasswordEncoder 加密方式
        //return  new DelegatingPasswordEncoder(null, null);

        // 或者 BCryptPasswordEncoder 加密方式。参数为密钥迭代次数。
        //
        // 如果使用了加密，那么内存中配置的用户密码就不能再是 12346 了，而应该是密文

        //return new BCryptPasswordEncoder(10);

        // 考虑到一般用户是通过注册，然后密码需要存储到数据库，所以，定义 Service 对用户密码进行加密
    }

    /**
     * 重写 configure 方法，配置用户角色
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                // 基于内存的用户配置角色时不需要加 "ROLE_" 前缀
                // 用户一：用户名：root， 密码 123456， 具备 ADMIN 和 DBA 角色
                .withUser("root").password("123456").roles("ADMIN", "DBA")
                .and()
                // 用户二：用户名：admin， 密码 123456， 具备 ADMIN 和 DBA 角色
                .withUser("admin").password("123456").roles("ADMIN", "USER")
                .and()
                // 用户三：用户名：sang， 密码 123456， 具备 USER 角色
                .withUser("sang").password("123456").roles("USER");
    }

    /**
     * 重写 configure 方法，根据实际情况配置角色与受保护资源关系
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启 HttpSecurity 的配置
        http.authorizeRequests()
                // 用户访问 /admin/** 模式的 URL 必须具备 ADMIN 角色
                .antMatchers("/admin/**")
                .hasRole("ADMIN")

                // 用户访问 /user/** 模式的 URL 必须具备 ADMIN 或者 USER 角色
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN', 'USER')")

                // 用户访问 /db/** 模式的 URL 必须具备 ADMIN 和 DBA 角色
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")

                // 除了以上指定的 URL 模式之外，用户访问其他 URL 必须认证（登录）后
                .anyRequest()
                .authenticated()

                /* *******  基本的配置  ******* */ /*

                // 开启表单登录。即默认的登录页面
                .and()
                .formLogin()
                // 配置登录接口为 /login   即可以直接调用 /login 接口发起 POST 请求进行登录。用户名命名为 username，密码命令为 password
                .loginProcessingUrl("/login")
                // 表示和登录相关接口不需要认证即可访问
                .permitAll()

                // 关闭 CSRF
                .and()
                .csrf()
                .disable();                 */

                /* *******  基本的配置  ******* */


                /* *******  详细的配置  ******* */

                .and()
                // 开启表单登录
                .formLogin()
                // 指定登录页面
                //.loginPage("/login_page")
                // 指定登录请求处理接口
                .loginProcessingUrl("/login")
                // 自定义认证所需用户名参数名。默认 username
                .usernameParameter("username")
                // 自定义认证所需密码参数名。默认 password
                .passwordParameter("password")
                // 自定义登录成功处理逻辑
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        Authentication auth)
                            throws IOException {
                        Object principal = auth.getPrincipal();
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter out = res.getWriter();
                        res.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", principal);
                        ObjectMapper objectMapper = new ObjectMapper();
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                // 自定义登录失败处理逻辑
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse res,
                                                        AuthenticationException e)
                            throws IOException, ServletException {
                        res.setContentType("application/json;charset=utf-8");
                        PrintWriter out = res.getWriter();
                        res.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);
                        if (e instanceof LockedException)
                            map.put("msg", "账户被锁定，登录失败");
                        else if (e instanceof BadCredentialsException)
                            map.put("msg", "账户名或密码输入错误，登录失败");
                        else if (e instanceof DisabledException)
                            map.put("msg", "账户被禁用，登录失败");
                        else if (e instanceof AccountExpiredException)
                            map.put("msg", "账户已过期，登录失败");
                        else if (e instanceof CredentialsExpiredException)
                            map.put("msg", "密码已过期，登录失败");
                        else
                            map.put("msg", "登录失败");
                        ObjectMapper objectMapper = new ObjectMapper();
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })

                // 注销登录的相关配置
                .and()
                // 开启注销登录配置
                .logout()
                // 配置注销登录请求 URL，默认 /logout
                .logoutUrl("/logout")
                // 清除身份认证信息，默认 true
                .clearAuthentication(true)
                // 使 Session 失效，默认 true
                .invalidateHttpSession(true)
                // 自定义注销时的操作。如 清除 Cookies 等，Spring Security 默认有提供一些常见实现
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest req,
                                       HttpServletResponse res,
                                       Authentication auth) {

                    }
                })
                // 自定义注销成功后的操作。如返回 JSON 或页面跳转。
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse res,
                                                Authentication auth) throws IOException, ServletException {
                        // 跳转到登录页面
                        res.sendRedirect("/login_page");
                    }
                })

                /* *******  详细的配置  ******* */

                //登录相关接口不需要认证即可访问
                .permitAll()
                // 关闭 CSRF
                .and()
                .csrf()
                .disable();
    }
}
