package org.sang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    /**
     * Realm 可以是自定义 Realm，也可以是 Shiro 提供的 Realm
     *
     * @return
     */
    @Bean
    public Realm realm(){
        TextConfigurationRealm realm = new TextConfigurationRealm();
        // 配置两个用户 sang/123 和 admin/123 分别对应角色 user 和 admin，user 具有 read 权限，admin 具有 read 、 write 权限
        realm.setUserDefinitions("sang=123,user\n admin=123,admin");
        realm.setRoleDefinitions("admin=read,write\n user=read");
        return realm;
    }

    /**
     * 配置基本过滤规则
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // /login 可以匿名请求
        chainDefinition.addPathDefinition("/login", "anon");
        // /doLogin 可以匿名请求
        chainDefinition.addPathDefinition("/doLogin", "anon");
        // /logout 注销登录请求
        chainDefinition.addPathDefinition("/logout","logout");
        // 其他请求  需要认证后才能访问
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * 支持在 Thymeleaf 中使用 Shiro 标签，
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
