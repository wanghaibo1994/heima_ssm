package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

public interface ITravellerDao {

    @Select("select * from TRAVELLER where id in \n" +
            "(select travellerid from order_traveller \n" +
            "where orderid  = #{ordersId})")
    public Traveller findById(String ordersId);
}
