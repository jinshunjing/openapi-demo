package org.jim.open.config;

import lombok.extern.slf4j.Slf4j;
import org.jim.open.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web MVC config
 *
 * @author author
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    HandlerInterceptor userInterceptor(){
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/*/user/greeting")
                .addPathPatterns("/*/user/bye");
    }

}
