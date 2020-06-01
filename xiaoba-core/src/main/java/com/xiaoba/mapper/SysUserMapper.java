package com.xiaoba.mapper;

import com.xiaoba.entity.SysUser;
import org.apache.ibatis.annotations.*;

/**
 * @author zhouning
 */
@Mapper
public interface SysUserMapper {

    /**
     * 向sys_user中插入数据
     * @param sysUser
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("insert into sys_user(user_name,user_password,user_role,user_avatar_path,user_sex,user_telephone,user_email) " +
            "values(#{userName},#{userPassword},#{userRole},#{userAvatarPath},#{userSex},#{userTelephone},#{userEmail})")
    public int insertUser(SysUser sysUser);

    /**
     *  删除 SysUser 通过id
     * @param userId
     * @return
     */
    @Delete("delete from sys_user where user_id=#{userId}")
    public int deleteUserById(Integer userId);

    /**
     * 更新 sys_user
     * @param sysUser
     */
    @Update("update sys_user set user_name=#{userName},user_password=#{userPassword},user_role=#{userRole},user_avatar_path=#{userAvatarPath},user_sex=#{userSex},user_telephone=#{userTelephone},user_email=#{userEmail} where user_id=#{userId}")
    int updateUser(SysUser sysUser);

    /**
     * 通过 userId 查询 SysUser
     * @param userId
     * @return
     */
    @Select("select * from sys_user where user_id=#{userId}")
    SysUser selectById(Integer userId);

    /**
     * 通过用户名和密码查询用户
     * @param userName
     * @return
     */
    @Select("select * from sys_user where user_name=#{userName} ")
    SysUser getUserByName(String userName);


}
