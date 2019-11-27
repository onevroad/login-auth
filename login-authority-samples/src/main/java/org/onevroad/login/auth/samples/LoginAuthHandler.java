package org.onevroad.login.auth.samples;

import lombok.extern.slf4j.Slf4j;
import org.onevroad.login.auth.core.AuthHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginAuthHandler implements AuthHandler {

    @Override
    public boolean execute(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        boolean isLogin = !StringUtils.isEmpty(token);
        if (!isLogin) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                response.getWriter().append("Please Login!");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return isLogin;
    }
}
