package org.onevroad.login.auth.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
@ComponentScan
public class LoginWebMvcConfigurer implements WebMvcConfigurer {

    private RequestMessageConverter converter;

    private AuthInterceptor interceptor;

    public LoginWebMvcConfigurer(RequestMessageConverter converter, AuthInterceptor interceptor) {
        this.converter = converter;
        this.interceptor = interceptor;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        if (converter == null) {
            log.error("Could not find a RequestMessageConverter implement class, please check it.");
        } else {
            resolvers.add(new LoginRequestMessageHandlerMethodArgumentResolver(converter));
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }


}
