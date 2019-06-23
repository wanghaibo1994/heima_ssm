package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.ISyslogDao;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService {

    @Autowired
    private ISyslogDao syslogDao;


    @Override
    public List<SysLog> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return syslogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog) throws Exception {
        syslogDao.save(sysLog);
    }
}
