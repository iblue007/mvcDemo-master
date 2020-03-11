package com.xjt.service;


import com.xjt.model.Address;
import com.xjt.model.Banner;

import java.util.List;

public interface IAddressService {

    List<Address> getAddressList(int userId) ;

    void deleteAddressById(int addressId);

    void InsertAddress(Address address) ;

    void updateAddress(Address address) ;

    void updateAddressState(Address address) ;

    void resetAddressState() ;
}
