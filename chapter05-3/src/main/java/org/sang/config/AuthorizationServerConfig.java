package org.sang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 授权服务器配置
 *
 * 自定义类继承 AuthorizationServerConfigurerAdapter 完成对授权服务器的配置
 */

@Configuration
// @EnableAuthorizationServer 注解开启授权服务器
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 注入 AuthenticationManager 用来支持 password 模式
    @Autowired
    AuthenticationManager authenticationManager;
    // 注入 RedisConnectionFactory 用来完成Redis 缓存，将令牌信息存储到 Redis 缓存中
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    // 注入 UserDetailsService， 为刷新 token 提供支持
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 提供加密方式
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 配置 password 授权模式
                .withClient("password")
                // 表示 OAuth 2 中的授权模式为 password 和 refresh_token 两种
                // 在标准的 OAuth 2 协议中，授权模式并不包括 refresh_token 但是在 Spring Security 的实现中将其归为一种
                // 因此如果要实现 access_token 的刷新，就需要添加这样一种授权模式
                .authorizedGrantTypes("password", "refresh_token")
                // 配置 access_token 的过期时间
                .accessTokenValiditySeconds(1800)
                // 配置资源 id
                .resourceIds("rid")
                .scopes("all")
                // 配置加密后的密码 —— 明文：123
                .secret("$2a$10$gUlrbz4MqrAghARIwjsfNu3NDvheoKAz8lrJ5s9tjwt/umNMYk4mm");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置令牌存储，主要用于支持 password 模式以及令牌的刷新
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 支持 client_id 和 client_secret 做登录认证
        security.allowFormAuthenticationForClients();
    }
}
