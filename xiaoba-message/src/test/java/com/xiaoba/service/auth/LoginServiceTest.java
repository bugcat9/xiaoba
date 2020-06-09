package com.xiaoba.service.auth;

import com.xiaoba.entity.SysUser;
import com.xiaoba.entity.SysUserToken;
import com.xiaoba.service.LoginService;
import com.xiaoba.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;


    @Test
    public void login() {
        Map<String,Object> map= loginService.login("user01","123456");
        System.out.print(map);
    }

    @Test
    public void getInfo() {
        Map<String,Object> map= loginService.login("user01","123456");
        String token=(String)map.get("token");
        SysUser user= loginService.getInfo(token);
        System.out.println(user);
    }

    @Test
    public void logout() {
        Map<String,Object> map= loginService.login("user01","123456");
        String token=(String)map.get("token");
        loginService.logout(token);
    }
}