package com.xiaoba.controller;

import com.google.code.kaptcha.Producer;
import com.xiaoba.AuthToken;
import com.xiaoba.entity.SysUser;
import com.xiaoba.service.CaptchaService;
import com.xiaoba.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
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
import java.util.HashMap;
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
    @GetMapping("/captcha.jpg")
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
     * @return json文件，其中有个token用户判断用户登录是否成功
     */
    @GetMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@RequestParam("userName") String userName,
                                    @RequestParam("userPassword") String userPassword){
        //需要输入验证码

        Map<String,Object> map = loginService.login(userName,userPassword);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        AuthToken authToken = new AuthToken((String) map.get("token"));
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(authToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();

            return map;
        } catch (AuthorizationException e) {
            e.printStackTrace();

            return map;
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
