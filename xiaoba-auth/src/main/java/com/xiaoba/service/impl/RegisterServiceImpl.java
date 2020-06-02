package com.xiaoba.service.impl;

import com.xiaoba.entity.SysUser;
import com.xiaoba.entity.SysUserToken;
import com.xiaoba.mapper.SysUserMapper;
import com.xiaoba.service.RegisterService;
import com.xiaoba.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouning
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public boolean registerUser(String userName, String userPassword) {

        SysUser sysUser = sysUserMapper.getUserByName(userName);
        //判断是否已经注册了
        if (sysUser!=null){
            return false;
        }

        //创建用户
        sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setUserPassword(userPassword);
        sysUser.setUserRole("user");
        sysUser.setUserAvatarPath("1.jpg");
        sysUser.setUserSex(1);

        //进行数据库的插入
        if(sysUserMapper.insertUser(sysUser)==1){
            return true;
        }

        return false;
    }

    @Override
    public boolean updateUser(String token, String userName, Integer sex, String telephone, String email) {
        SysUserToken sysUserToken = tokenService.queryByToken(token);
        if (sysUserToken==null){
            return false;
        }

        SysUser sysUser = sysUserMapper.selectById(sysUserToken.getUserId());
        sysUser.setUserName(userName);
        sysUser.setUserSex(sex);
        sysUser.setUserTelephone(telephone);
        sysUser.setUserEmail(email);
        if (sysUserMapper.updateUser(sysUser)==1){
            return true;
        }

        return false;
    }
}
