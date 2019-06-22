package com.itheima.ssm.dao;

import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findById"))
    })
    UserInfo findById(String id) throws Exception;

    @Update("update users set username = #{username} , email = #{email} , phoneNum = #{phoneNum}, status = #{status} where id = #{id}")
    void updateUser(UserInfo userInfo) throws Exception;
    @Update("update users set password = #{password}  where id = #{id}")
    void updateUserPassword(UserInfo userInfo) throws Exception;


    @Delete("delete from users_role where userId in #{id}")
    void deleteByUserIdFllUser_role(String id) throws Exception;
    @Delete("delete from users where id in #{id}")
    void deleteUserById(String id)  throws Exception;
}
