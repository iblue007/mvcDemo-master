package com.xjt.service.impl;

import com.xjt.dao.IBannerDao;
import com.xjt.dao.IOrderDao;
import com.xjt.model.Banner;
import com.xjt.model.Order;
import com.xjt.service.IBannerService;
import com.xjt.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDao bannerDao;


    @Override
    public List<Order> getOrderList() {
        return bannerDao.getOrderList();
    }

    @Override
    public List<Order> getOrderListByUid(int userId) {
        return bannerDao.getOrderListByUid(userId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return bannerDao.getOrderById(orderId);
    }

    @Override
    public void insertOrder(Order order) {
        bannerDao.insertOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        bannerDao.updateOrder(order);
    }

    @Override
    public void deleteOrderById(int orderId) {
        bannerDao.deleteOrderById(orderId);
    }
}
