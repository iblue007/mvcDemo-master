package com.xjt.service.impl;

import com.xjt.dao.ICategoryDao;
import com.xjt.dao.IGoodDao;
import com.xjt.model.Category;
import com.xjt.model.Good;
import com.xjt.service.ICategoryService;
import com.xjt.service.IGoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    @Resource
    private ICategoryDao categoryDao;
    @Override
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }

    @Override
    public void insertCategory(Category category) {
        categoryDao.insertCategory(category);
    }
}
