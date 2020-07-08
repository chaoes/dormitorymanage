package me.wtclmy.project.config;

import me.wtclmy.project.interceptor.ManagerInterceptor;
import me.wtclmy.project.interceptor.StudentInterceptor;
import me.wtclmy.project.interceptor.WebIntercrptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther:chaoe
 * @date:2020/7/6
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebIntercrptor()).
                addPathPatterns("/**").
                excludePathPatterns("/dist/**","/plugins/**");
        registry.addInterceptor(new ManagerInterceptor()).addPathPatterns("/manager/**");
        registry.addInterceptor(new StudentInterceptor()).addPathPatterns("/student/**");
    }
}
