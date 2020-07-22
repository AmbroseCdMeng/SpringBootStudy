package org.sang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 *  资源服务器配置
 *
 *  自定义类继承 ResourceServerConfigurerAdapter 成对资源服务器的配置
 */

@Configuration
// @EnableResourceServer 注解开启资源服务器配置
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 配置资源 id ，这里资源 id 与 授权服务器中的资源 id 一致
        // 设置这些资源仅基于令牌认证
        resources.resourceId("rid").stateless(true);
    }

    // Spring Security 中的 HttpSecurity 优先级高于资源服务器中的配置
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("admin")
                .antMatchers("/user/**")
                .hasRole("user")
                .anyRequest()
                .authenticated();
    }
}
