package com.xiaoba.service;

import java.awt.image.BufferedImage;

/**
 * 验证码service层
 * @author zhouning
 */
public interface CaptchaService {
    /**
     * 获取验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证验证码
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}
