package com.demo.service;

import com.demo.entity.User;

public interface UserService extends IService<User> {

    public User login(String uid, String password);

    public Boolean edit(User user);
}
