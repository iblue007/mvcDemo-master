package com.xjt.controller;

import com.alibaba.fastjson.JSON;
import com.xjt.model.ResultMsg;
import com.xjt.model.User;
import com.xjt.service.IUserService;
import com.xjt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/userLogin")
    public ResultMsg userLogin(@RequestBody User user) throws Exception{
        ResultMsg resultMsg = new ResultMsg();
        User user1 = userService.userLogin(user);
        if(user1 != null){
            String s = JSON.toJSONString(user1);
            resultMsg.setMessage(s);
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        }else {
            resultMsg.setMessage("密码或者账号错误");
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/userList")
    public ResultMsg getUserList() throws Exception {
        ResultMsg resultMsg = new ResultMsg();
//        ModelAndView mv = new ModelAndView();
        List<User> userLiset = userService.getUserLiset();
//        mv.addObject("userLiset", userLiset);
//        mv.setViewName("userLiset");
        if (userLiset != null && userLiset.size() > 0) {
            try {
                String s = JSON.toJSONString(userLiset);
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
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/selectUserById")
    public ResultMsg selectUserById(int id) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        try {
            User user = userService.selectUserById(id);
            resultMsg.setMessage(JSON.toJSONString(user) + "");
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("useradd");
        return mv;
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public ResultMsg insertUser(@RequestBody User user, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        String mobile = user.getMobile();
        String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
        user.setSdate(currentTime);
        boolean success = userService.checkAccount(mobile);
        if (success) {
            resultMsg.setMessage("该账户已经存在");
            resultMsg.setSuccess(false);
        } else {
            try {
                user.setRole(1);
                userService.addUser(user);
                resultMsg.setMessage("操作成功");
                resultMsg.setSuccess(true);
                resultMsg.setCode(200);
            } catch (Exception e) {
                e.printStackTrace();
                resultMsg.setMessage(e.getMessage());
                resultMsg.setSuccess(false);
            }
        }
        return resultMsg;
    }

    @RequestMapping(value = "/udpate")
    @ResponseBody
    public ResultMsg updateUser(@RequestBody User user, Model model) {
        ResultMsg resultMsg = new ResultMsg();
        try {
            userService.updateUser(user);
            resultMsg.setMessage(JSON.toJSONString(user) + "");
            resultMsg.setSuccess(true);
            resultMsg.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/udpateAddress")
    @ResponseBody
    public ResultMsg udpateAddress(@RequestBody User user, Model model) {
        ResultMsg resultMsg = new ResultMsg();
        try {
            userService.udpateAddress(user);
            resultMsg.setMessage(JSON.toJSONString(user) + "");
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
