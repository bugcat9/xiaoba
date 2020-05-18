package com.xiaoba.mapper;

import com.xiaoba.bean.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhouning
 */
public interface SysUserMapper {

    /**
     * 通过 userId 查询 SysUser
     * @param userId
     * @return
     */
    @Select("select * from sys_user where userId=#{userId}")
    SysUser getUserById(Integer userId);

    /**
     *  SysUser，因为iuserId是自增的所以只需要其他的东西
     * @param sysUser
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    @Insert("insert into sys_user(userName,userPassword,createTime,updateTime) values(#{userName,userPassword,createTime,updateTime})")
    public int insertUser(SysUser sysUser);


}
