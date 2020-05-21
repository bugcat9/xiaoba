package com.xiaoba;

import com.xiaoba.bean.SysUser;
import com.xiaoba.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserMapperTest {
    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    void insertUserTest(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("zhouning");
        sysUser.setUserPassword("123456");
        sysUser.setUserAvatarPath("1.jpg");
        sysUser.setUserRole("user");
        sysUser.setUserSex(1);
        int a = sysUserMapper.insertUser(sysUser);
        System.out.println(a);
    }

    @Test
    void updateUserTest(){
        SysUser sysUser = sysUserMapper.getUserById(16);
        sysUser.setUserPassword("123aaa");
        sysUserMapper.updateUser(sysUser);
        System.out.println(sysUser);
    }

    @Test
    void getUserByIdTest(){
       SysUser sysUser = sysUserMapper.getUserById(1);
       System.out.println(sysUser);
    }

    @Test
    void getUserTest(){
        String userName = "zhouning";
        String userPassword = "123456";
        SysUser sysUser = sysUserMapper.getUser(userName, userPassword);
        System.out.println(sysUser);
    }

    @Test
    void deleteUserByIdTest(){
        int a = sysUserMapper.deleteUserById(17);
        System.out.println(a);
    }
}
