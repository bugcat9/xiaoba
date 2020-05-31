package com.xiaoba.service.impl;

import com.xiaoba.entity.SysUser;
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


        Result result = new Result(3);
//        判断sysUser是否为空已经userPassword是否正确
        if (sysUser!=null && sysUser.getUserPassword().equals(userPassword)) {
            String token = tokenService.createToken(sysUser.getUserId());
            result.put("token", token);
            String avatar = "http://39.99.203.80:8080/images/"+sysUser.getUserAvatarPath();
            result.put("avatar", avatar);
            String access = "admin";
            result.put("access", access);
        }else {
            result.put("token", -1+"");
        }

        return result;
    }

    @Override
    public SysUser getInfo(String token) {
        Integer id = Integer.valueOf(token);
        return sysUserMapper.selectById(id);
    }

}
