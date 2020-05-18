package com.xiaoba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
