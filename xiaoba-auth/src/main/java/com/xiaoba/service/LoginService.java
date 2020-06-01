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
     Map<String,Object> login(String userName, String userPassword);

    /**
     * 通过 token 得到用户信息
     * @param token
     * @return
     */
     SysUser getInfo(String token);

     void logout(String token);

}
