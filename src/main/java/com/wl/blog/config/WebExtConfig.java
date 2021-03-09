package com.wl.blog.config;

import com.wl.blog.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebExtConfig implements WebMvcConfigurer {
    @Resource
    loginInterceptor loginInterceptor;

    @Override
    //扩展替代Controller
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("index.html").setViewName("index");
    }

    @Override
    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/user/**").
                excludePathPatterns("/user/login").
                excludePathPatterns("/user/toLogin").
                addPathPatterns("/");
    }

}
