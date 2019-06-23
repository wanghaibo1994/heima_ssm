package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISyslogService {


    public List<SysLog> findAll(int page, int pageSize) throws Exception;

    void save(SysLog sysLog) throws Exception;
}
