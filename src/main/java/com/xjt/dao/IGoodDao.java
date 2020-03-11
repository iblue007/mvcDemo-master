package com.xjt.dao;

import com.xjt.model.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodDao {

    Good selectGoodById(@Param("goodId") int goodId);

    List<Good> getGoodList(@Param("row") int row, @Param("page") int page);

//    boolean checkGoodExist(String id);

    List<Good> getGoodListByCategoryId(@Param("categoryId") int categoryId) ;

    List<Good> getGoodListByKey(@Param("key") String key) ;

    void addGood(Good goods);

    void updateGood(Good goods);
}
