package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Scope("prototype")
@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService syslogService;


    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法
// 主要获取访问时间、访问的类、访问的方法

    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void before(JoinPoint jp){
        //获取执行前时间
        startTime = new Date();
        // 获取访问的类
        Signature signature = jp.getSignature();
        executionClass = signature.getDeclaringType();
        // 访问的方法
        MethodSignature methodSigna = (MethodSignature) signature;
        executionMethod = methodSigna.getMethod();
    }


    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取执行时长
        Long executionTime  = System.currentTimeMillis() - startTime.getTime();
        //获取URL
        StringBuffer urL = request.getRequestURL();
        //获取ip
        String ip = request.getRemoteAddr();
        //获取名称
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setMethod("[类名] "+executionClass.getName()+"[方法名]"+executionMethod.getName());
        sysLog.setVisitTime(startTime);
        sysLog.setExecutionTime(executionTime);
        sysLog.setUsername(name);
        sysLog.setUrl(urL.toString());

        if (!urL.toString().contains("sysLog")){
            syslogService.save(sysLog);
        }
    }



}
