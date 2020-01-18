# login-authority [![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This component will help you to verify login and automatically inject the login information that your defined.
It supports you to define the login information by yourself.

## Quick Start
### add maven dependency
```maven
<dependency>
    <groupId>org.onevroad</groupId>
    <artifactId>login-authority-core</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### define the login information
```java
public class LoginUser {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
```

### implement the interface of message converter
```java
@Component
public class LoginRequestMessageConverter implements RequestMessageConverter {

    @Override
    public Object convert(HttpServletRequest request, MethodParameter parameter,
                              Type paramType) {
        String token = request.getHeader("token");
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(token);
        return loginUser;
    }
}
```

### implement the interface of auth handler
```java
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
```

### add annotation for verifying login and injecting the login infomation
- the annotation of inject the login infomation: @LoginRequest
- the annotation of verify login: @LoginAuth
```java
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
```
