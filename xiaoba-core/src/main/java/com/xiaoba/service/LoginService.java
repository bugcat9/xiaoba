package com.xiaoba.service;

import com.xiaoba.entity.SysUser;

import java.util.Map;

/**
 * @author zhouning
 */
public interface LoginService {

    /**
     *
     * @param userName
     * @param userPassword
     * @return
     */
    public Map<String,Object> login(String userName, String userPassword);

    /**
     * 通过 token 得到用户信息
     * @param token
     * @return
     */
    public SysUser getInfo(String token);
}
