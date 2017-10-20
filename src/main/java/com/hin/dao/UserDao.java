package com.hin.dao;

import java.util.List;

import com.hin.entity.User;

public interface UserDao {

    User login(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void addUser(String userName);

    void deleteUser(String userName);

    void deleteAll();
}
