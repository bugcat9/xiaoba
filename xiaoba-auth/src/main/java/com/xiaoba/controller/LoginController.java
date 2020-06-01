package com.xiaoba.controller;

import com.xiaoba.AuthToken;
import com.xiaoba.entity.SysLoginForm;
import com.xiaoba.entity.SysUser;
import com.xiaoba.exception.ErrorEnum;
import com.xiaoba.service.CaptchaService;
import com.xiaoba.service.LoginService;
import com.xiaoba.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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


    /**
     * 获得验证码图片
     * @param request
     * @param response
     * @param uuid 生成 验证码的 id
     * @throws IOException
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");
//        System.out.println(uuid);
        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        IOUtils.closeQuietly(out);
    }

    /**
     *
     * @param form 登录的表单
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public Map<String,Object> login(SysLoginForm form){
        //需要输入验证码
        boolean captcha=captchaService.validate(form.getUuid(),form.getCaptcha());
        if(!captcha){
            // 验证码不正确
            return  Result.error(ErrorEnum.CAPTCHA_WRONG);
        }
        Map<String,Object> map = loginService.login(form.getUserName(),form.getUserPassword());
        String token = (String) map.get("token");

        if (token==null){
            return map;
        }

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        AuthToken authToken = new AuthToken(token);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(authToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            return Result.error(ErrorEnum.NO_AUTH);
        } catch (AuthorizationException e) {
            e.printStackTrace();
//            return Result.error(ErrorEnum.NO_AUTH);
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
    public void logout(String token){
        loginService.logout(token);
    }

    @GetMapping("/captcha")
    public String toCaptcha(){
        return "captcha";
    }
}
