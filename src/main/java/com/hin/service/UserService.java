package com.hin.service;

import java.util.List;
import java.util.Map;

import com.hin.entity.User;

public interface UserService {

	User login(Map<String, String> userLoginInfo);

    User getUserByName(String userName);

    List<User> getAllUsers();

    void addUser(Map<String, Object> userRegisterInfo);

    void deleteUser(String userName);

    void deleteAll();
}
