package com.xjt.service;


import com.xjt.model.Category;
import com.xjt.model.Good;

import java.util.List;

public interface ICategoryService {

    List<Category> getCategoryList() ;

    void insertCategory(Category category) ;
}
