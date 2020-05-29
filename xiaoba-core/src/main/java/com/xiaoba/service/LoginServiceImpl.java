package com.xiaoba.service;

import com.xiaoba.entity.SysUser;
import com.xiaoba.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//import com.xiaoba.mapper.SysUserMapper;

/**
 * @author zhouning
 */
@Service
@EnableCaching
public class LoginServiceImpl implements LoginService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Map<String,Object> login(String userName, String userPassword){
        System.out.println(userName);
        System.out.println(userPassword);

        //字符串序列化器
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        SysUser sysUser=(SysUser)redisTemplate.opsForValue().get("CUGBAR:USER::"+userName);
        if(sysUser==null) {
            sysUser = sysUserMapper.getUserByName(userName);
            if(sysUser!=null){
                //放进缓存中
                redisTemplate.opsForValue().set("CUGBAR:USER::"+userName,sysUser);
            }
        }

        Map<String,Object> map = new HashMap<>(3);
        //判断sysUser是否为空已经userPassword是否正确
        if (sysUser!=null && sysUser.getUserPassword().equals(userPassword)){

            //设置 token
            map.put("token", sysUser.getUserId()+"");
            //设置 头像
            String avatar = "http://39.99.203.80:8080/images/"+sysUser.getUserAvatarPath();
            map.put("avatar", avatar);
            //设置 权限
            String access = "admin";
            map.put("access", access);
        }else {
            map.put("token", -1+"");
        }

        return map;
    }

    @Cacheable(value = "CUGBAR:USER",key = "#userName")
    @Override
    public SysUser getInfo(String userName) {
        return sysUserMapper.getUserByName(userName);
    }
}
