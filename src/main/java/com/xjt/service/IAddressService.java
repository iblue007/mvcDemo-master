package com.xjt.service;


import com.xjt.model.Address;
import com.xjt.model.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAddressService {

    List<Address> getAddressList(int userId) ;

    List<Address> getAddressDefault( int userId) ;

    void deleteAddressById(int addressId);

    void InsertAddress(Address address) ;

    void updateAddress(Address address) ;

    void updateAddressState(Address address) ;

    void resetAddressState() ;
}
