package com.xiaoba.controller;

import com.xiaoba.AuthToken;
import com.xiaoba.entity.SysLoginForm;
import com.xiaoba.entity.SysUser;
import com.xiaoba.exception.ErrorEnum;
import com.xiaoba.exception.MyException;
import com.xiaoba.service.CaptchaService;
import com.xiaoba.service.LoginService;
import com.xiaoba.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
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
@Api(tags = "登录相关接口")
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
    @ApiOperation("得到验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid",value = "验证id")
    })
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/png");
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
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名"),
            @ApiImplicitParam(name = "userPassword",value = "密码"),
            @ApiImplicitParam(name = "captcha",value = "验证码"),
            @ApiImplicitParam(name = "uuid",value = "uuid")
    })
    @GetMapping("/login")
    @ResponseBody
    public Map<String,Object> login(SysLoginForm form){
        //需要输入验证码
        boolean captcha=captchaService.validate(form.getUuid(),form.getCaptcha());
        if(!captcha){
            // 验证码不正确
            throw new MyException();
        }
        Map<String,Object> map = loginService.login(form.getUserName(),form.getUserPassword());
        String token = (String) map.get("token");

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        AuthToken authToken = new AuthToken(token);
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(authToken);

        return map;
    }

    /**
     * 查询用户信息
     * @param token
     * @return
     */
    @ApiOperation("得到用户信息")
    @ApiImplicitParam(name = "token",value = "用户token")
    @GetMapping("/user")
    @ResponseBody
    public SysUser getInfo(@RequestParam("token")  String token){
        return loginService.getInfo(token);
    }

    @ApiOperation("退出登录")
    @ApiImplicitParam(name = "token",value = "用户token")
    @ResponseBody
    @GetMapping("/logout")
    public String logout(String token){
        System.out.println("退出登录");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        loginService.logout(token);
        return "登出";
    }

    @GetMapping("/captcha")
    public String toCaptcha(){
        return "captcha";
    }
}
