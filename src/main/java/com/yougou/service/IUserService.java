package com.yougou.service;

import com.alibaba.fastjson.JSONObject;
import com.yougou.pojo.User;

import java.util.List;


public interface IUserService {
    List<User> getUser(User user);

    Integer getUserCount(User user);

    Integer login(String  loginName, String pwd);

    JSONObject sendEmail(String mail);

    JSONObject verifyEmailCode(String mail, String code);

    JSONObject changePwd(String mail, String newPwd);
}
