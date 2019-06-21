package com.itheima.ssm.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
public class Role implements Serializable {
    private String id;
    private String roleName;//角色名称
    private String roleDesc;//角色描述
    private List<Permission> permissions;//资源权限  多对多关系
    private List<UserInfo> users;//用户  多对多关系

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
