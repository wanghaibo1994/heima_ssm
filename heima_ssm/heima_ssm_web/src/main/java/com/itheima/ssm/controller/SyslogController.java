package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {

    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(int page, int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = syslogService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }
}
