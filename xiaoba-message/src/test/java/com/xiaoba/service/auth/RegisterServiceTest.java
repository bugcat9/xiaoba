package com.xiaoba.service.auth;

import com.xiaoba.constans.SysConstants;
import com.xiaoba.entity.SysUser;
import com.xiaoba.service.LoginService;
import com.xiaoba.service.RegisterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RegisterServiceTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    LoginService loginService;

    @Test
    public void registerUser() {
        String uuid= UUID.randomUUID().toString();
        boolean result=registerService.registerUser(uuid,"12345678","12345678@qq.com");
        Assert.assertTrue(result);
    }

    /**
     * 集成测试
     */
    @Test
    public void updateUser() {
        String uuid= UUID.randomUUID().toString();
        boolean result=registerService.registerUser(uuid,"12345678","12345678@qq.com");
        Map<String,Object> map= loginService.login(uuid,"12345678");
        String token=(String)map.get("token");

        boolean res=registerService.updateUser(token,"T"+uuid,"123456",0,"12345678901","32324@qq.com");
        SysUser user =loginService.getInfo(token);
        Assert.assertEquals("T"+uuid,user.getUserName());
        Assert.assertEquals("123456",user.getUserPassword());
        Assert.assertEquals((Integer)0,user.getUserSex());
        Assert.assertEquals("12345678901",user.getUserTelephone());
        Assert.assertEquals("32324@qq.com",user.getUserEmail());
        System.out.println(user);
    }
}