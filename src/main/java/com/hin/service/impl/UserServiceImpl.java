package com.hin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hin.dao.UserDao;
import com.hin.entity.User;
import com.hin.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    //public List<User> userList = getAllUsers();

    @Resource
    private UserDao userDao;

    public User login(Map<String, String> userLoginInfo) {
    	User user = getUserByName(userLoginInfo.get("userName"));
    
    	if (user != null && user.getPassword() != null 
    			&& user.getPassword().equals(userLoginInfo.get("password"))) {
    		return user;
    	}
        return null;
    }

    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(Map<String, Object> userRegisterInfo) {
    	User user = new User();
    	user.setUserName((String)userRegisterInfo.get("userName"));
    	user.setPassword((String)userRegisterInfo.get("password"));
    	user.setEmail((String)userRegisterInfo.get("email"));
    	user.setPhone((String)userRegisterInfo.get("phone"));
    	user.setAddress((String)userRegisterInfo.get("address"));
    	user.setAuthority((Integer)userRegisterInfo.get("authority"));
    	
        userDao.addUser(user);
    }

    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }

}
