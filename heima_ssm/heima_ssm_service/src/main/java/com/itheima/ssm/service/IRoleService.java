package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {


    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    Role findByRoleId(String roleId) throws Exception;
}
