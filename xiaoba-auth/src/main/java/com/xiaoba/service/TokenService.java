package com.xiaoba.service;

import com.xiaoba.entity.SysUserToken;
import com.xiaoba.util.Result;

/**
 *  token service层
 *  主要是生成token
 * @author zhouning
 */
public interface TokenService {
    /**
     * 生成Token
     * @param userId
     * @return
     */
    String createToken(Integer userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 退出登录
     * @param userId
     */
    void logout(Integer userId);

    /**
     * 续期
     * @param userId
     * @param token
     */
    void refreshToken(Integer userId, String token);
}
