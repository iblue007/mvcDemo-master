package com.xjt.dao;

import com.xjt.model.Banner;
import com.xjt.model.Category;
import com.xjt.model.Good;

import java.util.List;

public interface IBannerDao {

    List<Banner> getBannerList() ;

    void deleteAllBanner();

    void insertBanenr(Banner banner) ;
}
