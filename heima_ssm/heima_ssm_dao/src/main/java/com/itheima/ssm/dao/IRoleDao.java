package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id",javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findById"))

    })
    List<Role> findById(String userId) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findUserByIdAndAllRole(String userId) throws Exception;

    @Insert("insert into users_role (userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param(value = "userId") String userId,@Param(value = "roleId") String roleId) throws Exception;

    @Select("select * from role where id  = #{roleId}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id",javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findById"))

    })
    Role findByRoleId(String roleId);

    @Select("select * from role where id  in (select roleId from users_role where userId = #{userId})")
    List<Role> deleteUserByIdAndAllRole(String userId);

    @Delete("delete from users_role where userId= #{userId} and roleId = #{roleId} ")
    void deleteRoleToUser(@Param(value = "userId") String userId,@Param(value = "roleId") String roleId);
}
