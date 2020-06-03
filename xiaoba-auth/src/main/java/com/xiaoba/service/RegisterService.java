package com.xiaoba.service;

/**
 * @author zhouning
 */
public interface RegisterService {

    /**
     * 进行注册
     * @param userName 用户名
     * @param userPassword  密码
     * @return 注册是否成功
     */
     boolean registerUser(String userName,String userPassword,String email);

     boolean updateUser(String token,String userName,Integer sex,String telephone,String email);
}
