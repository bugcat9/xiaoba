package com.xiaoba.service.impl;

import com.xiaoba.entity.SysUser;
import com.xiaoba.entity.SysUserToken;
import com.xiaoba.mapper.SysUserMapper;
import com.xiaoba.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Set<String> getUserPermissions(Integer userId) {
        return null;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return null;
    }

    @Override
    public SysUser queryUser(Integer userId) {
        return null;
    }

    @Override
    public void refreshToken(Integer userId, String accessToken) {

    }
}
