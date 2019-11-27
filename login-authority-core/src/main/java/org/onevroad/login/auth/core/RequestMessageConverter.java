package org.onevroad.login.auth.core;

import org.springframework.core.MethodParameter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

public interface RequestMessageConverter {

    Object convert(HttpServletRequest request, MethodParameter parameter, Type paramType);

}
