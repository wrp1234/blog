package com.wrp.blog.common.config;

import com.wrp.blog.common.interceptor.UserInterceptor;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author wrp
 * @since 2024-11-19 23:32
 **/
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private UserInterceptor userInterceptor;
    private UrlProperties urlProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userInterceptor).addPathPatterns("/**")
//                .excludePathPatterns(urlProperties.getExcludes());
    }



    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(false)
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}
