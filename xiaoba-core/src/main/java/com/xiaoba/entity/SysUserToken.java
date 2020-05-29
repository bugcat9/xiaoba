package com.xiaoba.entity;

/**
 * 存放 id 和 token
 * 方便对两者进行查询
 */
public class SysUserToken {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String token;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
