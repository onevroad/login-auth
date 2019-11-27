package org.onevroad.login.auth.samples;

import org.onevroad.login.auth.core.LoginAuth;
import org.onevroad.login.auth.core.LoginRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login() {
        return "success";
    }

    @LoginAuth
    @RequestMapping(method = RequestMethod.GET, path = "/user/get")
    public String getUser(@LoginRequest LoginUser loginUser) {
        return loginUser.toString();
    }
}
