package com.demo.service.impl;

import com.demo.dao.Impl.UserDaoImpl;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.entity.dto.UserMap;
import com.demo.service.UserService;
import com.demo.util.AuthUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public Boolean saveOne(User entity) {
        //user already exists
        if (userDao.selectCountByUid(entity.getUid()) > 0) {
            return false;
        }
        return userDao.insertOne(entity);
    }


    public User login(String uid, String password) {
        return userDao.selectUserByUidAndPassword(uid, password);
    }

    @Override
    public Boolean edit(User user) {
        if (AuthUtil.isLogin()) {
            return false;
        }
        user.setUid(UserMap.getUid());
        return userDao.editUser(user);
    }
}
