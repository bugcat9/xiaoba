package com.xiaoba.controller;

import com.google.code.kaptcha.Producer;
import com.xiaoba.entity.SysUser;
import com.xiaoba.service.CaptchaService;
import com.xiaoba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


/**
 * @author zhouning
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    private CaptchaService captchaService;
    @Resource
    private Producer kaptchaProducer;

    /**
     *
     * @param response
     * @param uuid
     * @throws IOException
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");
        System.out.println(uuid);
        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     * @param userName 用户名
     * @param userPassword  密码
     * @param session
     * @return json文件，其中有个token用户判断用户登录是否成功
     */
    @GetMapping("/login")
    @ResponseBody
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

    /**
     * 查询用户信息
     * @param token
     * @return
     */
    @GetMapping("/user")
    @ResponseBody
    public SysUser getInfo(@RequestParam("token")  String token){

        return loginService.getInfo(token);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session){
        if (session.getAttribute("token")!=null){
            session.removeAttribute("token");
        }
    }

    @GetMapping("/captcha")
    public String toCaptcha(){
        return "captcha";
    }
}
