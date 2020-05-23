package com.xiaoba.controller;

import com.xiaoba.bean.SysUser;
import com.xiaoba.service.LoginService;
import com.xiaoba.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhouning
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 登录
     * @param userName 用户名
     * @param userPassword  密码
     * @param session
     * @return json文件，其中有个token用户判断用户登录是否成功
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestParam("userName") String userName,
                                    @RequestParam("userPassword") String userPassword,
                                    HttpSession session){
        //先直接返回,后面再进行判断
        Map<String,Object> map = loginService.login(userName,userPassword);
        String token = (String) map.get("token");
        if (!token.equals("-1")){
            //说明登录成功
            //放在session当中，用于判断是否登录
            session.setAttribute("token", token);
        }
        return map;
    }

    @PostMapping("/user")
    public SysUser getInfo(@RequestParam("token")  String token,HttpSession session){

        return loginService.getInfo(token);
    }

}
