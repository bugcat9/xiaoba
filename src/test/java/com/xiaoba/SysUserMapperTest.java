package com.xiaoba;

import com.xiaoba.bean.SysUser;
import com.xiaoba.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest

public class SysUserMapperTest {
//    @Autowired
//    SysUserMapper sysUserMapper;
//
//    @Test
//    void insertUserTest(){
//        SysUser sysUser = new SysUser();
//        sysUser.setUserName("zhouningTest");
//        sysUser.setUserPassword("123456");
//        sysUser.setUserAvatarPath("1.jpg");
//        sysUser.setUserRole("user");
//        sysUser.setUserSex(1);
//        int result = sysUserMapper.insertUser(sysUser);
//        assert (result==1);
//
//    }
//
//    @Test
//    void updateUserTest(){
//        SysUser sysUser = sysUserMapper.getUserByName("zhouningTest");
//        sysUser.setUserPassword("123aaa");
//        sysUserMapper.updateUser(sysUser);
//        System.out.println(sysUser);
//        assert (sysUser!=null);
//    }
//
//
//    @Test
//    void deleteUserByIdTest(){
//        SysUser sysUser = sysUserMapper.getUserByName("zhouningTest");
//        int result = sysUserMapper.deleteUserById(sysUser.getUserId());
//        assert (result==1);
//    }
}
