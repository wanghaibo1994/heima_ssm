package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao ProductDao;

    @Override
    public List<Product> findAll(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> all = ProductDao.findAll();
        return all;
    }

    @Override
    public void save(Product product) {
        ProductDao.save(product);
    }
}
