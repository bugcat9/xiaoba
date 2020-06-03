package com.xiaoba.controller;

import com.xiaoba.entity.SysUser;
import com.xiaoba.service.MailService;
import com.xiaoba.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author zhouning
 */

@RestController
public class RegisterController{

    @Autowired
    RegisterService registerService;

    @Autowired
    MailService mailService;
    /**
     * 注册
     * @param userName 用户名
     * @param userPassword 密码
     * @return
     */
    @GetMapping("/register")
    public boolean register(String userName, String userPassword,String email,String verifyCode){
        if (!mailService.validate(email, verifyCode)){
            return false;
        }

        boolean res ;
        try {
            res =  registerService.registerUser(userName, userPassword, email);
        }catch (Exception e){
            return false;
        }
        return res;
    }

    @RequestMapping("/user/update")
    public boolean updateUser(String token,String userName,Integer sex,String telephone,String email){
        return registerService.updateUser(token,userName,sex,telephone,email);
    }

    @GetMapping("/sendemail")
    public boolean sendTemplateMail(String reciver,String verifyCode){
        try {
            mailService.sendCheckCode(reciver, verifyCode);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
