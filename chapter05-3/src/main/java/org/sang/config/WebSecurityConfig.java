package org.sang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring Security 配置
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Bean 将注入授权服务器配置类中使用
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$gUlrbz4MqrAghARIwjsfNu3NDvheoKAz8lrJ5s9tjwt/umNMYk4mm")
                .roles("admin")
                .and()
                .withUser("sang")
                .password("$2a$10$gUlrbz4MqrAghARIwjsfNu3NDvheoKAz8lrJ5s9tjwt/umNMYk4mm")
                .roles("user");
    }

    // Spring Security 中的 HttpSecurity 优先级高于资源服务器中的配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**/")
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
