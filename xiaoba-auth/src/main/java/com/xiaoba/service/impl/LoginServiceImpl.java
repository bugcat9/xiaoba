package com.xiaoba.service.impl;

import com.xiaoba.entity.SysUser;
import com.xiaoba.entity.SysUserToken;
import com.xiaoba.exception.ErrorEnum;
import com.xiaoba.exception.MyException;
import com.xiaoba.mapper.SysUserMapper;
import com.xiaoba.service.LoginService;
import com.xiaoba.service.TokenService;
import com.xiaoba.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouning
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    TokenService tokenService;

    @Override
    public Map<String,Object> login(String userName, String userPassword){
        System.out.println(userName);
        System.out.println(userPassword);
        SysUser sysUser =sysUserMapper.getUserByName(userName);

        Result result = new Result();
        //判断sysUser是否为空已经userPassword是否正确
        if (sysUser!=null && sysUser.getUserPassword().equals(userPassword)) {
            String token = tokenService.createToken(sysUser.getUserId());

            result.put("token", token);
            String avatar = "http://39.99.203.80:8080/images/"+sysUser.getUserAvatarPath();
            result.put("avatar", avatar);
            String access = "admin";
            result.put("access", access);
        }else {
            throw new MyException();
        }
        return result;
    }

    @Override
    public SysUser getInfo(String token) {
        SysUserToken sysUserToken = tokenService.queryByToken(token);
        if (sysUserToken==null) {
            return null;
        }
        return sysUserMapper.selectById(sysUserToken.getUserId());
    }


    @Override
    public void logout(String token) {
        SysUserToken sysUserToken = tokenService.queryByToken(token);
        if (sysUserToken==null) {
            return;
        }
        tokenService.logout(sysUserToken.getUserId());
    }

}
