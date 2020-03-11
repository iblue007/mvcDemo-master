package com.xjt.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xjt.model.Address;
import com.xjt.model.Banner;
import com.xjt.model.ResultMsg;
import com.xjt.model.User;
import com.xjt.service.IAddressService;
import com.xjt.service.IBannerService;
import com.xjt.service.IUserService;
import com.xjt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/addressList")
    public ResultMsg getAddressList(int userId) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        List<Address> addressList = addressService.getAddressList(userId);
        if (addressList != null && addressList.size() > 0) {
            try {
                String s = JSON.toJSONString(addressList);
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

    @ResponseBody
    @RequestMapping("/getAddressDefault")
    public ResultMsg getAddressDefault(int userId) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        List<Address> address = addressService.getAddressDefault(userId);
        if (address != null && address.size() > 0) {
            try {
                String s = JSON.toJSONString(address);
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


    @RequestMapping(value = "/insertAddress")
    @ResponseBody
    public ResultMsg insertAddress(@RequestBody Address address, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        System.out.print("======address:"+address.toString());
        try {
            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
            address.setAddressDate(currentTime);
            if(addressService.getAddressList(address.getUserId()).size() == 0){
                address.setState(1);
                User user = new User();
                user.setId(address.getUserId());
                user.setAddress(address.getAddress());
                user.setAddressDetail(address.getAddressDetail());
                userService.udpateAddress(user);
            }
            addressService.InsertAddress(address);
            resultMsg.setMessage("添加成功");
            resultMsg.setCode(220);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/updateAddress")
    @ResponseBody
    public ResultMsg updateAddress(@RequestBody Address address, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        System.out.print("======address:"+address.toString());
        try {
//            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
//            address.setAddressDate(currentTime);
            addressService.updateAddress(address);
            resultMsg.setMessage("添加成功");
            resultMsg.setCode(200);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/updateAddressState")
    @ResponseBody
    public ResultMsg updateAddressState(@RequestBody Address address, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        System.out.print("======address:"+address.toString());
        try {
            addressService.resetAddressState();
            addressService.updateAddressState(address);
            resultMsg.setMessage("添加成功");
            resultMsg.setCode(200);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/deleteAddressById")
    @ResponseBody
    public ResultMsg deleteAddressById(int addressId) {

        ResultMsg resultMsg = new ResultMsg();
        try {
            addressService.deleteAddressById(addressId);
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

}
