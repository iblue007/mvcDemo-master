package com.xjt.service.impl;

import com.xjt.dao.IGoodDao;
import com.xjt.dao.IUserDao;
import com.xjt.model.Good;
import com.xjt.model.User;
import com.xjt.service.IGoodService;
import com.xjt.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodService")
public class GoodServiceImpl implements IGoodService {

    @Resource
    private IGoodDao goodDao;

    public Good selectGoodById(int goodId) {
        return goodDao.selectGoodById(goodId);
    }

//    @Override
//    public boolean checkAccount(String mobile) {
//        int n = userDao.checkAccount(mobile);
//        return n>0;
//    }

    @Override
    public void addGood(Good good) {
        goodDao.addGood(good);
    }

    @Override
    public void updateGood(Good good) {
        goodDao.updateGood(good);
    }

    @Override
    public List<Good> getGoodList(int row,int page) {
        return goodDao.getGoodList(row,page);
    }

    @Override
    public List<Good> getGoodListByCategoryId(int categoryId) {
        return goodDao.getGoodListByCategoryId(categoryId);
    }

    @Override
    public List<Good> getGoodListByKey(String key) {
        return goodDao.getGoodListByKey(key);
    }
}
