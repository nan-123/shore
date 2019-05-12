package com.yougou.service.impl;

import com.yougou.mapper.UserMapper;
import com.yougou.pojo.User;
import com.yougou.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public Integer getUserCount(User user) {
        return userMapper.getUserCount(user);
    }

    @Override
    public Integer login(String  loginName, String pwd) {
        Integer code = 200;
        if (loginName == null || loginName.trim().length() == 0){
            code = 500;
            return code;
        }
        if (pwd == null || pwd.trim().length() == 0){
            code= 501;
            return code;
        }
        User user = new User();
        user.setLoginName(loginName);
        List<User> list = this.getUser(user);
        if (list == null || list.size() == 0){
            code = 502;
            return code;
        }

        User user1 = list.get(0);
        String pwd1 = user1.getPwd();
        if (!pwd1.equals(pwd)){
            code = 503;
            return code;
        }
        return code;
    }
}
