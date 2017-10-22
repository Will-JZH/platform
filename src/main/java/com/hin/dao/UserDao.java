package com.hin.dao;

import java.util.List;

import com.hin.entity.User;

public interface UserDao {

    User login(User user);

    User getUserByName(String userName);

    List<User> getAllUsers();

    void addUser(User user);

    void deleteUser(String userName);

    void deleteAll();
}
