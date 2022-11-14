package com.example.redisdemo.repository;

import com.example.redisdemo.model.User;

import java.util.List;

public interface UserDao{
    boolean saveUser(User user);

    List<User> fetchAllUser();
}
