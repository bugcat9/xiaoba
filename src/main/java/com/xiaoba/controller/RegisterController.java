package com.xiaoba.controller;

import com.xiaoba.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouning
 */

@RestController
public class RegisterController{

    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public boolean register(@RequestParam("userName") String userName,
                           @RequestParam("userPassword") String userPassword){

        return registerService.registerUser(userName, userPassword);
    }
}
