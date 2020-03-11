package com.xjt.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.tools.corba.se.idl.constExpr.Or;
import com.xjt.model.*;
import com.xjt.service.ICategoryService;
import com.xjt.service.IOrderService;
import com.xjt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/orderList")
    @ResponseBody
    public ResultMsg getOrderList(@RequestBody String param) throws Exception {
        int userId = 0;
        int loginUserRole = 1;
        try {
            if(!StringUtils.isEmpty(param)){
                JSONObject json = JSONObject.parseObject(param);
                userId = (int) json.get("userId");
                loginUserRole = (int) json.get("loginUserRole");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultMsg resultMsg = new ResultMsg();
        List<Order> orderList = null;
        if(loginUserRole == 0){
            orderList = orderService.getOrderListByUid(userId);
        }else {
            orderList = orderService.getOrderList();
        }
        if (orderList != null && orderList.size() > 0) {
            try {
                String s = JSON.toJSONString(orderList);
                resultMsg.setMessage(s);
                resultMsg.setSuccess(true);
                resultMsg.setCode(200);
            } catch (Exception e) {
                e.printStackTrace();
                resultMsg.setMessage(e.getMessage());
                resultMsg.setSuccess(false);
            }
        } else {
            resultMsg.setMessage("没有数据");
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/getOrderById")
    @ResponseBody
    public ResultMsg getOrderById(int orderId) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            try {
                String s = JSON.toJSONString(order);
                resultMsg.setMessage(s);
                resultMsg.setSuccess(true);
                resultMsg.setCode(200);
            } catch (Exception e) {
                e.printStackTrace();
                resultMsg.setMessage(e.getMessage());
                resultMsg.setSuccess(false);
            }
        } else {
            resultMsg.setMessage("没有数据");
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        }
        return resultMsg;
    }


    @RequestMapping(value = "/deleteOrderById")
    @ResponseBody
    public ResultMsg deleteOrderById(int orderId) {

        ResultMsg resultMsg = new ResultMsg();
        try {
            orderService.deleteOrderById(orderId);
            resultMsg.setMessage("操作成功");
            resultMsg.setCode(200);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }


    @RequestMapping(value = "/insertOrder")
    @ResponseBody
    public ResultMsg insertCategory(@RequestBody Order order) {

        ResultMsg resultMsg = new ResultMsg();
        try {
            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
            order.setOdate(currentTime);
            orderService.insertOrder(order);
            resultMsg.setMessage("操作成功");
            resultMsg.setCode(200);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/udpateOrder")
    @ResponseBody
    public ResultMsg updateGood(@RequestBody Order order) {
        ResultMsg resultMsg = new ResultMsg();
        try {
            orderService.updateOrder(order);
            resultMsg.setMessage(JSON.toJSONString(order) + "");
            resultMsg.setMessage("操作成功");
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

}
