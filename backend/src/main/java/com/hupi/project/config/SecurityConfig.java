package com.hupi.project.config;

import com.hupi.project.common.security.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * Security 配置
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private  JwtAccessDeniedHandler jwtAccessDeniedHandler;
    /**
     * 自定义用户认证逻辑
     */
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * 自定义错误
     */
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     *成功退出类
     */
    @Resource
    private  JwtLogoutSuccessHandler jwtLogoutSuccessHandler;


    private static final String[] URL_WHITELIST = {
            //登录  注册 验证码
            "/login", "/register", "/captchaImage",
            //静态资源
            "/**/*.html"
            ,"/**/*.css"
            ,"/**/*.js"
            ,"/profile/**"
            //swagger
            ,"/swagger-ui.html"
            ,"/swagger-resources/**"
            ,"/webjars/**"
            ,"/*/api-docs"
            ,"/druid/**"
            ,"doc.html"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
        .cors()
        .and()
        .csrf()
        .disable()//csrf攻击 关闭

        //登录登出配置
            .formLogin()
                .usernameParameter("userAccount")
                .passwordParameter("userPassword")
                .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailHandler)
                    .and()
                    .logout()
                    .logoutSuccessHandler(jwtLogoutSuccessHandler)
        //session禁用配置
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //无状态

        //拦截规则配置
                .and()
                    .authorizeRequests()
                        .antMatchers(URL_WHITELIST).permitAll() //白名单 放行
                            .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
        //异常处理配置
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        //自定义处理配置
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager));

        return http.build();
    }




    /**
     *
     * 创建BCryptPasswordEncoder注入Spring容器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
