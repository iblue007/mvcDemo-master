package com.xjt.service;

import com.xjt.model.Good;
import com.xjt.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodService {

    public Good selectGoodById(int goodId);

//    boolean checkGoodExist(String id);
    void addGood(Good good);

    void updateGood(Good good);

    List<Good> getGoodList(int row,int page);

    List<Good> getGoodListByCategoryId(int categoryId) ;

    List<Good> getGoodListByKey( String key) ;
}
