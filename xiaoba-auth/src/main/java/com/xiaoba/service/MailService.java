package com.xiaoba.service;

/**
 * @author zhouning
 */
public interface MailService {
    /**
     * 发送邮件，主要用于发送验证码
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
     void sendMail(String to, String subject, String content);

    /**
     *发送验证码
     * @param reciver
     * @param verifyCode
     */
     void sendCheckCode(String reciver,String verifyCode);

    /**
     * 验证验证码
     * @param reciver
     * @param verifyCode
     * @return
     */
     boolean validate(String reciver, String verifyCode);
}
