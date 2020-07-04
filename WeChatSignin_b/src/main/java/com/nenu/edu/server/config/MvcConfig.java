package com.nenu.edu.server.config;

import com.nenu.edu.server.web.filter.AuthFilter;
import com.nenu.edu.server.web.handler.ControllerUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * @Author: Lu Jingyuan
 * @Description:角色枚举
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthFilter authFilter;

    @Autowired
    private ControllerUserArgumentResolver controllerUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authFilter).excludePathPatterns("/api/user/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(controllerUserArgumentResolver);
    }
}
