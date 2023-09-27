package org.onevroad.login.auth.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Type;

public interface RequestMessageConverter {

    Object convert(HttpServletRequest request, MethodParameter parameter, Type paramType);

}
