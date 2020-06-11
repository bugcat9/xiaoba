package com.xiaoba.service.impl;

import com.xiaoba.TokenGenerator;
import com.xiaoba.constans.RedisKeyConstants;
import com.xiaoba.entity.SysUserToken;
import com.xiaoba.service.TokenService;
import com.xiaoba.util.RedisUtils;
import com.xiaoba.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author zhouning
 */
@Service
public class TokenServiceImpl implements TokenService {
    /**
     *     12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String createToken(Integer userId) {
        // 生成一个token
        String token = TokenGenerator.generateValue();
        //得到缓存的key
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;
        //判断是否生成过token
        String tokenInRedis=redisUtils.get(userIdKey);
        if(!StringUtils.isEmpty(tokenInRedis)){
            // 将原来的token删除
            redisUtils.delete(RedisKeyConstants.MANAGE_SYS_USER_TOKEN+tokenInRedis);
        }
        // 将token存进redis
        redisUtils.set(tokenKey,userId,EXPIRE);
        redisUtils.set(userIdKey,token,EXPIRE);

        return token;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        String userId=redisUtils.get(tokenKey);
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        SysUserToken sysUserToken=new SysUserToken();
        sysUserToken.setToken(token);
        sysUserToken.setUserId(Integer.parseInt(userId));
        return sysUserToken;
    }

    @Override
    public void logout(Integer userId) {
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;
        String token=redisUtils.get(userIdKey);
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        redisUtils.delete(userIdKey);
        redisUtils.delete(tokenKey);
    }

    @Override
    public void refreshToken(Integer userId, String token) {
        //对缓存进行更新
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;
        redisUtils.updateExpire(tokenKey);
        redisUtils.updateExpire(userIdKey);
    }
}
