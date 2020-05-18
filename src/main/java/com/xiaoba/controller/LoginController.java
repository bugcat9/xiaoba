package com.xiaoba.controller;

import com.xiaoba.bean.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhouning
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public Map<String,String> login(@RequestParam("userName") String username,
                                    @RequestParam("password") String password){
        Map<String,String> map = new HashMap<>(3);
        map.put("token",username);
        return map;
    }

    @PostMapping("/user")
    public SysUser getInfo(@RequestParam("token")  String token){
        SysUser sysUser = new SysUser();
        sysUser.setUserId(10);
        sysUser.setUserName("zhouning");
        sysUser.setUserPassword("123456");
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUser;
    }

}
