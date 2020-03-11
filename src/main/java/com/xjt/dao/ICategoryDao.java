package com.xjt.dao;

import com.xjt.model.Category;
import com.xjt.model.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICategoryDao {

    List<Category> getCategoryList() ;
    void insertCategory(Category category) ;
}
