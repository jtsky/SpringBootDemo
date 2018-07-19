package com.jin.springbootdemo.service;

import com.jin.springbootdemo.bean.User;
import com.jin.springbootdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao mUserDao;

    public List<User> getUsers() {
        return mUserDao.getUsers();
    }

    public User getUser(long id) {
        return mUserDao.getUser(id);
    }

    public int delUser(long id) {
        // 根据主键ID删除用户信息
        return mUserDao.delUser(id);
    }

    public int addUser(User user) {
        // 添加用户
        return mUserDao.addUser(user);
    }


    public int editUser(long id, User user) {
        // 根据主键ID修改用户信息
        return mUserDao.editUser(id, user);
    }

}
