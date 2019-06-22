package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/deleteUserById.do")
    public String deleteUserById(String id) throws Exception {
        userService.deleteUserById(id);


        return "redirect:findAll.do";
    }

    @RequestMapping("/updateUser.do")
    public String updateUser(UserInfo userInfo) throws Exception {

        userService.updateUser(userInfo);

        return "redirect:findAll.do";
    }


    @RequestMapping("/updateUserById.do")
    public ModelAndView updateUserById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =  userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-update");
        return mv;
    }


    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(String userId,@RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {

        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> rolesList =   userService.findUserByIdAndAllRole(id);
        mv.addObject("roleList",rolesList);
        mv.addObject("id",id);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("deleteRoleToUser.do")
    public String deleteRoleToUser(String userId,@RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {

        userService.deleteRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("deleteUserByIdAndAllRole.do")
    public ModelAndView deleteUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> rolesList =   userService.deleteUserByIdAndAllRole(id);
        mv.addObject("roleList",rolesList);
        mv.addObject("id",id);
        mv.setViewName("user-role-delete");
        return mv;
    }


    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos =  userService.findAll();
        mv.addObject("userList",userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =  userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
