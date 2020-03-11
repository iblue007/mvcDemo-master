package com.xjt.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xjt.model.Banner;
import com.xjt.model.Category;
import com.xjt.model.ResultMsg;
import com.xjt.service.IBannerService;
import com.xjt.service.ICategoryService;
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
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private IBannerService bannerService;

    @ResponseBody
    @RequestMapping("/bannerList")
    public ResultMsg getBannerList() throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        List<Banner> bannerList = bannerService.getBannerList();
        if (bannerList != null && bannerList.size() > 0) {
            try {
                String s = JSON.toJSONString(bannerList);
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


    @RequestMapping(value = "/insertBanenr")
    @ResponseBody
    public ResultMsg insertBanenr(@RequestBody Banner banner, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        System.out.print("======category:"+banner.toString());
        try {
            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
            banner.setBdate(currentTime);
            if(!StringUtils.isEmpty(banner.getPicStr())){
                String picName = "banner_add_"+System.currentTimeMillis();
                String test = addPic(banner.getPicStr(), picName);
                banner.setPicStr(test);
                bannerService.insertBanenr(banner);
            }else {
                bannerService.insertBanenr(banner);
            }
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

    @RequestMapping(value = "/deleteAllBanner")
    @ResponseBody
    public ResultMsg deleteAllBanner() {

        ResultMsg resultMsg = new ResultMsg();
        try {
            bannerService.deleteAllBanner();
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
