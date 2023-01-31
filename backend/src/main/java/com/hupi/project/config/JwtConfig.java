package com.hupi.project.config;


import com.hupi.project.aop.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    /** 拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor)
                //增加拦截路径
                .addPathPatterns("/**")
                //不拦截路径
                .excludePathPatterns("/**/doc.html"
                        ,"/**/webjars/**"
                        ,"/**/swagger-resources"
<<<<<<< HEAD
                        , "/**/v3/**"
                        ,"/**/user/login");
=======
                        , "/**/v3/**");
>>>>>>> 3dea64886e711b48d0959e1fe7f354f01ad245b5



    }
}