package com.xiaoba.service;

import com.xiaoba.bean.SysUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.Map;

import static org.testng.Assert.*;

@SpringBootTest
public class LoginServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    LoginService loginService;

    @org.junit.jupiter.api.Test
    public void testErroPassword(){
        System.out.print("开始测试登录时密码错误的情况......");
        Map<String,Object> map=loginService.login("user02","000000");
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("token"),"-1");
        //System.out.print("测试结束......");
    }

    @org.junit.jupiter.api.Test
    public void testErroName(){
        System.out.print("开始测试登录时用户不存在的情况......");
        Map<String,Object> map=loginService.login("111111","000000");
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("token"),"-1");
    }

    @org.junit.jupiter.api.Test
    public void testLogin() {
        System.out.print("开始测试正常登录......");
        Map<String,Object> map=loginService.login("user02","12345678");
        System.out.println("测试查询结果是否为空");
        Assert.assertNotNull(map);
        Assert.assertEquals(map.get("token"),"6");
    }

    @Test
    void testGetInfo() {
        SysUser user=loginService.getInfo("user02");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUserId().intValue(),6);
        Assert.assertEquals(user.getUserName(),"user02");
    }
}