package com.xjt.service.impl;

import com.xjt.dao.IAddressDao;
import com.xjt.dao.IBannerDao;
import com.xjt.model.Address;
import com.xjt.model.Banner;
import com.xjt.service.IAddressService;
import com.xjt.service.IBannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements IAddressService {

    @Resource
    private IAddressDao addressDao;


    @Override
    public List<Address> getAddressList(int userId) {
        return addressDao.getAddressList(userId);
    }

    @Override
    public void deleteAddressById(int id) {
        addressDao.deleteAddressById(id);
    }

    @Override
    public void InsertAddress(Address address) {
        addressDao.InsertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateAddress(address);
    }

    @Override
    public void updateAddressState(Address address) {
        addressDao.updateAddressState(address);
    }

    @Override
    public void resetAddressState() {
        addressDao.resetAddressState();
    }
}
