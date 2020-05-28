package com.xiaoba.service;

import com.xiaoba.bean.SysUser;
import com.xiaoba.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;



@SpringBootTest
public class RegisterServiceImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    RegisterService registerService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void testAutowired(){
        Assert.assertNotNull(registerService);
    }

    @Test
    public void testRegisterUser() {
        String uuid= UUID.randomUUID().toString();
        System.out.println("测试注册,注册用户名："+uuid);
        boolean result=registerService.registerUser(uuid,"12345678");
        Assert.assertTrue(result);

        SysUser user=sysUserMapper.getUserByName(uuid);
        Assert.assertNotNull(user);
        Assert.assertEquals(uuid,user.getUserName());

        System.out.println("测试注册已存在的用户，测试注册用户名："+uuid);
        result=registerService.registerUser(uuid,"12345678");
        Assert.assertFalse(result);
        user=sysUserMapper.getUserByName(uuid);
        Assert.assertNotNull(user);
        Assert.assertEquals(uuid,user.getUserName());
    }


}