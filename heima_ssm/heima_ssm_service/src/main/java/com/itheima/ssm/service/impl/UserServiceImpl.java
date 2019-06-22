package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo =null;
        try {
            userInfo =  userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }


        User user  = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {


        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        userInfo.setPassword(bpe.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {

        return userDao.findById(id);
    }

    @Override
    public List<Role> findUserByIdAndAllRole(String userId) throws Exception {

        return roleDao.findUserByIdAndAllRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            roleDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void deleteRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            roleDao.deleteRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> deleteUserByIdAndAllRole(String userId) {
        return roleDao.deleteUserByIdAndAllRole(userId);
    }

    @Override
    public void updateUser(UserInfo userInfo) throws Exception {
        userDao.updateUser(userInfo);
        if(userInfo.getPassword()!=null && userInfo.getPassword().length()>0 && !userInfo.getPassword().equals("null")){
            BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
            userInfo.setPassword(bpe.encode(userInfo.getPassword()));
            userDao.updateUserPassword(userInfo);
        }

    }

    @Override
    public void deleteUserById(String id) throws Exception {
        userDao.deleteByUserIdFllUser_role(id);
        userDao.deleteUserById(id);
    }
}
