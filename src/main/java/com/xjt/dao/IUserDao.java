package com.xjt.dao;

import com.xjt.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

    User selectUserById(int id);

    List<User> getUserList();

    int checkAccount(@Param("mobile") String mobile);

    void addUser(User user);

    void updateUser(User user);

    void udpateAddress(User user);

    User userLogin(User user);
}
