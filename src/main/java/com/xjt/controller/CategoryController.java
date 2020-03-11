package com.xjt.controller;

import com.alibaba.fastjson.JSON;
import com.xjt.model.Category;
import com.xjt.model.Good;
import com.xjt.model.ResultMsg;
import com.xjt.service.ICategoryService;
import com.xjt.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @ResponseBody
    @RequestMapping("/categoryList")
    public ResultMsg getCategoryList() throws Exception {
        ResultMsg resultMsg = new ResultMsg();
        List<Category> categoryList = categoryService.getCategoryList();
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


    @RequestMapping(value = "/insertCategory")
    @ResponseBody
    public ResultMsg insertCategory(@RequestBody Category category, Model model) {

        ResultMsg resultMsg = new ResultMsg();
        System.out.print("======category:"+category.toString());
        try {
            String currentTime = TimeUtil.timeStamp2Date(TimeUtil.timeStamp(), null);
            category.setCdate(currentTime);
            categoryService.insertCategory(category);
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

}
