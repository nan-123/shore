package com.yougou.service;

import com.yougou.pojo.User;

import java.util.List;


public interface IUserService {
    List<User> getUser(User user);

    Integer getUserCount(User user);

    Integer login(String  loginName, String pwd);
}
