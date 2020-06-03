package com.xiaoba.service;

public interface MailService {
    /**
     * 发送邮件
     * @param to 邮件收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
     void sendMail(String to, String subject, String content);

    /**
     *
     * @param reciver
     * @param verifyCode
     */
     void sendCheckCode(String reciver,String verifyCode);

     boolean validate(String reciver, String verifyCode);
}
