package com.xiaoba.service;

import com.xiaoba.bean.SysUser;
import com.xiaoba.mapper.SysUserMapper;
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

    @Override
    public Map<String,Object> login(String userName, String userPassword){
        System.out.println(userName);
        System.out.println(userPassword);
        SysUser sysUser =sysUserMapper.getUser(userName, userPassword);

        Map<String,Object> map = new HashMap<>(3);
        if (sysUser!=null){
            //设置 token
            map.put("token", sysUser.getUserId()+"");
            //设置 头像
            String avatar = "http://39.99.203.80:8081/images/"+sysUser.getUserAvatarPath();
            map.put("avatar", avatar);
            //设置 权限
            String access = "admin";
            map.put("access", access);
        }else {
            map.put("token", -1+"");
        }

        return map;
    }

    @Override
    public SysUser getInfo(String token) {
        Integer id = Integer.valueOf(token);
        return sysUserMapper.getUserById(id);
    }
}
