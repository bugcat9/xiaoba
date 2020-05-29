package com.xiaoba.controller;

import com.xiaoba.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouning
 */

@RestController
public class RegisterController{

    @Autowired
    RegisterService registerService;

    /**
     * 注册
     * @param userName 用户名
     * @param userPassword 密码
     * @return
     */
    @GetMapping("/register")
    public boolean register(@RequestParam("userName") String userName,
                           @RequestParam("userPassword") String userPassword){

        return registerService.registerUser(userName, userPassword);
    }
}
