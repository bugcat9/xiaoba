package com.xiaoba.service.impl;

import com.xiaoba.service.MailService;
import com.xiaoba.util.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.xiaoba.service.impl.CaptchaServiceImpl.CAPTCHA_EXPIRE;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisUtils redisUtils;

    public final static long VERIFY_CODE_EXPIRE = 60 * 5;
    /**
     * 邮件发送人
     */
    @Value("${mail.fromMail.addr}")
    private String from;

    @Autowired
    TemplateEngine templateEngine;

    @Override
    public void sendMail(String reciver, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(reciver);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);

    }

    @Override
    public void sendCheckCode(String reciver, String verifyCode) {
        String message = "您的注册验证码为："+verifyCode;
        sendMail(reciver, "注册验证码", message);
        // 存进redis,5分钟后过期,将邮箱的主人作为key
        redisUtils.set(reciver,verifyCode,CAPTCHA_EXPIRE);
    }

    @Override
    public boolean validate(String reciver, String verifyCode) {
        if(StringUtils.isEmpty(reciver)||StringUtils.isEmpty(verifyCode)){
            return false;
        }
        // 从redis中取
        String redisKey=reciver;
        String code=redisUtils.get(redisKey);
        //删除验证码
        redisUtils.delete(redisKey);
        if(verifyCode.equalsIgnoreCase(code)){
            return true;
        }
        return false;
    }
}
