# login-authority [![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

登录验证、登录用户信息自动注入  
支持自定义登录信息类

## Quick Start
### 引入依赖
```maven
<dependency>
    <groupId>org.onevroad</groupId>
    <artifactId>login-authority-core</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### 自定义登录信息类
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

### 实现请求信息转换接口
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

### 实现权限验证处理器接口
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

### 获取登录信息及添加权限验证注解
- 注入登录信息注解：@LoginRequest
- 权限验证注解：@LoginAuth
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
