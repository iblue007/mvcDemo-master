package com.xjt.dao;

import com.xjt.model.Banner;
import com.xjt.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDao {

    List<Order> getOrderList() ;

    List<Order> getOrderListByUid(@Param("userId") int userId) ;

    Order getOrderById(@Param("orderId") int orderId) ;

    void insertOrder(Order order) ;

    void updateOrder(Order order);

    void deleteOrderById(@Param("orderId") int orderId);
}
