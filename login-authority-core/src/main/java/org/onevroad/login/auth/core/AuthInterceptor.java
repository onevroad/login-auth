package org.onevroad.login.auth.core;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private AuthHandler authHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(LoginAuth.class)) {
            if (authHandler == null) {
                log.warn("Could not find an AuthHandler implement class");
                return true;
            }
            return authHandler.execute(request, response);
        }
        return true;
    }
}
