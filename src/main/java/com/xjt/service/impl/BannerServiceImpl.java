package com.xjt.service.impl;

import com.xjt.dao.IBannerDao;
import com.xjt.dao.ICategoryDao;
import com.xjt.model.Banner;
import com.xjt.model.Category;
import com.xjt.service.IBannerService;
import com.xjt.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bannerService")
public class BannerServiceImpl implements IBannerService {

    @Resource
    private IBannerDao bannerDao;

    @Override
    public List<Banner> getBannerList() {
        return bannerDao.getBannerList();
    }

    @Override
    public void deleteAllBanner() {
        bannerDao.deleteAllBanner();
    }

    @Override
    public void insertBanenr(Banner banner) {
        bannerDao.insertBanenr(banner);
    }
}
