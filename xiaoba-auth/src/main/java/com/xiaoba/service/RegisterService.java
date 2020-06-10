package com.xiaoba.service;

/**
 * @author zhouning
 */
public interface RegisterService {

    /**
     * 进行注册
     * @param userName 用户名
     * @param userPassword  密码
     * @param email
     * @return 注册是否成功
     */
     boolean registerUser(String userName,String userPassword,String email);

    /**
     * 更新用户信息
     * @param token
     * @param userName
     * @param password
     * @param sex
     * @param telephone
     * @param email
     * @return
     */
     boolean updateUser(String token,String userName,String password,Integer sex,String telephone,String email);
}
