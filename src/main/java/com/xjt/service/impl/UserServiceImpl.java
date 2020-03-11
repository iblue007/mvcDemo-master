package com.xjt.service.impl;

import com.xjt.dao.IUserDao;
import com.xjt.model.User;
import com.xjt.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUserById(int userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public boolean checkAccount(String mobile) {
        int n = userDao.checkAccount(mobile);
        return n>0;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void udpateAddress(User user) {
        userDao.udpateAddress(user);
    }

    @Override
    public List<User> getUserLiset() {
        return userDao.getUserList();
    }

    @Override
    public User userLogin(User user) {

        return userDao.userLogin(user);
    }
}
