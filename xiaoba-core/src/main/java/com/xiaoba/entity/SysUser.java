package com.xiaoba.entity;

import java.io.Serializable;

/**
 * @author zhouning
 */
public class SysUser implements Serializable {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private String userAvatarPath;
    private Integer userSex;
    private Integer userTelephoe;
    private String userEmail;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserTelephoe() {
        return userTelephoe;
    }

    public void setUserTelephoe(Integer userTelephoe) {
        this.userTelephoe = userTelephoe;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userAvatarPath='" + userAvatarPath + '\'' +
                ", userSex=" + userSex +
                ", userTelephoe=" + userTelephoe +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
