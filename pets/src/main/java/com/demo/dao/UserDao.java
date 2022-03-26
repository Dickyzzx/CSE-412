package com.demo.dao;

import com.demo.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    long selectCountByUid(String uid);

    User selectUserByUidAndPassword(String uid, String password);

    Boolean editUser(User user);

    List<User> selectAll(String name);
}
