package com.xjt.dao;

import com.xjt.model.Address;
import com.xjt.model.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IAddressDao {

    List<Address> getAddressList(@Param("userId") int userId) ;

    List<Address> getAddressDefault(@Param("userId") int userId) ;

    void deleteAddressById(@Param("addressId") int addressId);

    void InsertAddress(Address address) ;

    void updateAddress(Address address) ;

    void updateAddressState(Address address) ;

    void resetAddressState() ;

}
