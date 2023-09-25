package com.grumvalski.yogaLifebackend.config;

import com.grumvalski.yogaLifebackend.security.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor controllo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllo).addPathPatterns("/yogaLife/event/**");
    }
}
