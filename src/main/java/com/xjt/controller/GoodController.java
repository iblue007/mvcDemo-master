package com.xjt.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.log.LogUtils;
import com.xjt.model.Good;
import com.xjt.model.ResultMsg;
import com.xjt.service.IGoodService;
import com.xjt.util.TimeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private IGoodService goodService;

    @ResponseBody
    @RequestMapping("/goodListByKey")
    public ResultMsg getCategoryListByKey(String key) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        String keyNew = new String(key.getBytes("ISO8859-1"), "UTF-8");
        List<Good> categoryList = goodService.getGoodListByKey(keyNew);
        if (categoryList != null && categoryList.size() > 0) {
            try {
                String s = JSON.toJSONString(categoryList);
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
    @RequestMapping("/goodListByCategoryId")
    public ResultMsg getCategoryListByCategoryId(int categoryId) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        List<Good> categoryList = goodService.getGoodListByCategoryId(categoryId);
        if (categoryList != null && categoryList.size() > 0) {
            try {
                String s = JSON.toJSONString(categoryList);
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
    @RequestMapping("/goodList")
    public ResultMsg getGoodList(int row,int page) throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        int startIndex = (page - 1) * row;
        List<Good> userLiset = goodService.getGoodList(row,startIndex);
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
            resultMsg.setCode(200);
        }
        return resultMsg;
    }

    @ResponseBody
    @RequestMapping("/selectUserById")
    public ResultMsg selectUserById(int goodId) throws Exception {
//        ModelAndView mv = new ModelAndView();
//        Good good = goodService.selectGoodById(id);
//        mv.addObject("good", good);
//        mv.setViewName("good");
        ResultMsg resultMsg = new ResultMsg();
        Good good = goodService.selectGoodById(goodId);
        if (good != null) {
            try {
                String s = JSON.toJSONString(good);
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
    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("goodAdd");
        return mv;
    }

    @RequestMapping(value = "/insertGood")
    @ResponseBody
    public ResultMsg insertGood(@RequestBody Good good, Model model) {

        ResultMsg resultMsg = new ResultMsg();
//        String goodName = good.getGoodName();
//        String goodDetail = good.getGoodDetail();
//        double goodPrice = good.getGoodPrice();
//        double goodDiscount = good.getGoodDiscount();
        System.out.print("======good:"+good.toString());
        try {
            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
            good.setGdate(currentTime);
            if(!StringUtils.isEmpty(good.getGoodPic())){
                String picName = "good_add_"+System.currentTimeMillis();
                String test = addPic(good.getGoodPic(), picName);
                good.setGoodPic(test);
                goodService.addGood(good);
            }else {
                goodService.addGood(good);
            }
            resultMsg.setMessage("商品添加成功");
            resultMsg.setCode(200);
            resultMsg.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg.setMessage(e.getMessage());
            resultMsg.setSuccess(false);
        }
        return resultMsg;
    }

    @RequestMapping(value = "/udpate")
    @ResponseBody
    public ResultMsg updateGood(@RequestBody Good good, Model model) {
        ResultMsg resultMsg = new ResultMsg();
        try {
            String goodPic = good.getGoodPic();
            if(!StringUtils.isEmpty(goodPic)){
                if(goodPic.contains("http:")){

                }else {
                    String picName = "good_add_"+System.currentTimeMillis();
                    String test = addPic(good.getGoodPic(), picName);
                    good.setGoodPic(test);
                }
            }
            goodService.updateGood(good);
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

    private String addPic(String encodedImageStr,String fileName){
        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.getMimeDecoder().decode(encodedImageStr);
            String pinAddress = "D:/uploads/" + fileName+".jpg";
            FileOutputStream imageOutFile = new FileOutputStream(pinAddress);
            imageOutFile.write(imageByteArray);
            imageOutFile.close();
            System.out.println("Image Successfully Stored");
            String ip = InetAddress.getLocalHost().getHostAddress();
            if(!StringUtils.isEmpty(ip)){
                return "http://" + ip + ":8080/file/"+ fileName+".jpg";
            }
            return "http://localhost:8080/file/"+ fileName+".jpg";
        } catch (FileNotFoundException fnfe) {
            System.out.println("Image Path not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Exception while converting the Image " + ioe);
        }
        return "";
    }
}
