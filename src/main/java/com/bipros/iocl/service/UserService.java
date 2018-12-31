package com.bipros.iocl.service;

import com.bipros.iocl.entities.User;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void createAdmin(User user);
    User findOne(String email);
    boolean isUserPresent(String email);
    List<User> findAll();
    List<User> findByName(String name);
}
