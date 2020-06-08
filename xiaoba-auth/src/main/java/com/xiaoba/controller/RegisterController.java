package com.xiaoba.controller;

import com.xiaoba.entity.SysUser;
import com.xiaoba.service.MailService;
import com.xiaoba.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author zhouning
 */
@Api(tags = "注册接口")
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
    @ApiOperation("注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名"),
            @ApiImplicitParam(name = "userPassword",value = "密码"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "verifyCode",value = "验证码")
    })
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

    @ApiOperation("更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "token"),
            @ApiImplicitParam(name = "userName",value = "用户名"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "telephone",value = "手机号"),
            @ApiImplicitParam(name = "email",value = "邮件")
    }
    )
    @GetMapping("/user/update")
    public boolean updateUser(String token,String userName,String password,Integer sex,String telephone,String email){
        return registerService.updateUser(token,userName,password,sex,telephone,email);
    }

    @ApiOperation("发送邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reciver",value = "接收者"),
            @ApiImplicitParam(name = "verifyCode",value = "验证码")
    })
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
