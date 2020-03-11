package com.xjt.service;


import com.xjt.model.Banner;
import com.xjt.model.Category;

import java.util.List;

public interface IBannerService {

    List<Banner> getBannerList() ;

    void deleteAllBanner();

    void insertBanenr(Banner banner) ;
}
