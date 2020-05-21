package com.xiaoba.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginServiceImplTest {

    
    @Autowired
    LoginService loginService;



    @Before
    public void autowiredTest(){
        Assert.assertNotNull(loginService);
    }

    @Test
    public void erroPasswordTest(){
        Map<String,Object> map=loginService.login("user02","000000");
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("token"),"-1");
    }

    @Test
    void login() {
        Map<String,Object> map=loginService.login("user02","12345678");
        System.out.println("测试查询结果是否为空");
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("token"),"6");
    }

    @Test
    void getInfo() {


    }
}