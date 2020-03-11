package com.xjt.service;


import com.xjt.model.Banner;
import com.xjt.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderService {

    List<Order> getOrderList() ;

    List<Order> getOrderListByUid(int userId) ;

    Order getOrderById(int orderId) ;

    void insertOrder(Order order) ;

    void updateOrder(Order order);

    void deleteOrderById(int orderId);
}
