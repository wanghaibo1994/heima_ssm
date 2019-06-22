package com.itheima.ssm.service.impl;


import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) throws Exception {
        return permissionDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            permissionDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public Role findByRoleId(String roleId) throws Exception {
        return roleDao.findByRoleId(roleId);
    }
}
