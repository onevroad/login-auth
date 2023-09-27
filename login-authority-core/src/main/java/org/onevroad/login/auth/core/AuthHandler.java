package org.onevroad.login.auth.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthHandler {

    boolean execute(HttpServletRequest request, HttpServletResponse response);

}
