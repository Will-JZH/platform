package com.hin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hin.entity.User;
import com.hin.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/userlist.json")
    @ResponseBody
    public List<User> getUserList() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addUser/{userName}", method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@PathVariable("userName") String userName) {
        userService.addUser(userName);
    }

    @RequestMapping(value = "/removeUser/{userName}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
    }

    @RequestMapping(value = "/removeAllUsers", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeAllUsers() {
        userService.deleteAll();
    }

    @RequestMapping("/layout")
    public String getUserPartialPage() {
        return "users/layout";
    }

}
