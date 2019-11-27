package org.onevroad.login.auth.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthHandler {

    boolean execute(HttpServletRequest request, HttpServletResponse response);

}
