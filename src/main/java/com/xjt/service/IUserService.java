package com.xjt.service;

import com.xjt.model.User;

import java.util.List;

public interface IUserService {

    public User selectUserById(int userId);

    boolean checkAccount(String mobile);
    void addUser(User user);
    void updateUser(User user);
    void udpateAddress(User user);
    List<User> getUserLiset();
    User userLogin(User user);
}
