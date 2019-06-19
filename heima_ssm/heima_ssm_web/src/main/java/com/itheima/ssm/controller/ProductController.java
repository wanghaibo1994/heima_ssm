package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    /*@RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView mav = new ModelAndView();
        List<Product> all = iProductService.findAll();
        //PageInfo pageInfo = new PageInfo(ordersList);
        //        mv.addObject("pageInfo",pageInfo);
        mav.addObject("productList",all);
        mav.setViewName("product-list1");
        return mav;
    }*/

    @RequestMapping("findAll.do")
    public ModelAndView findAll(int page, int pageSize) throws Exception {

        ModelAndView mav = new ModelAndView();
        List<Product> all = iProductService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(all);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("product-list");
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        iProductService.save(product);
        return "redirect:findAll.do";
    }
}
