package com.yougou.controller;

import com.alibaba.fastjson.JSONObject;
import com.yougou.mapper.UserMapper;
import com.yougou.pojo.User;
import com.yougou.service.IUserService;
import com.yougou.util.RedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private RedisUtils redisUtils;


    @Resource
    private UserMapper userMapper;


    @ResponseBody
    @RequestMapping(value = "/getUser")
    JSONObject getUser(User user){
        if (user == null){
            user = new User();
        }
        if (user.getPageSize() != null && user.getPageSize() != null && user.getPageSize().length()>0 && user.getPageSize().length() > 0){
            Integer pageNum = Integer.valueOf(user.getPageNum());
            Integer pageSize = Integer.valueOf(user.getPageSize());
            user.setPageNum((String.valueOf((pageNum-1)*pageSize)));

        }
        JSONObject jsonObject = new JSONObject();
        Integer userCount = userService.getUserCount(user);
        jsonObject.put("users",userService.getUser(user));
        jsonObject.put("count", userCount);
        return  jsonObject;
    }

    @RequestMapping("/delUser")
    @ResponseBody
    JSONObject  delUser(String id){
        JSONObject jsonObject = new JSONObject();
        try {
            User user = new User();
            user.setId(id);
            userMapper.delete(user);
            jsonObject.put("del", "0");
        } catch (Exception e) {
            jsonObject.put("del", "1");
        }
        return jsonObject;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    JSONObject  addUser(User user){
        JSONObject jsonObject = new JSONObject();
        try {
            long nowDate = new Date().getTime();

            String sid = String.valueOf(nowDate);
            user.setId(sid);
           userMapper.insert(user);
            jsonObject.put("add", "0");
        } catch (Exception e) {
            jsonObject.put("add", "1");
            e.printStackTrace();
        }
        return jsonObject;
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    JSONObject  getUserById(String id){
        JSONObject jsonObject = new JSONObject();
            User user = userMapper.selectByPrimaryKey(id);
            jsonObject.put("user", user);
        return jsonObject;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    JSONObject  updateUser(User user){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            userMapper.updateByPrimaryKey(user);
            jsonObject.put("update", "0");
        } catch (Exception e) {
            jsonObject.put("update", "1");
        }
        return jsonObject;
    }

    @RequestMapping("/login")
    @ResponseBody
    JSONObject login(String loginName, String pwd){
        JSONObject jsonObject = new JSONObject();
        Integer login = userService.login(loginName, pwd);
        jsonObject.put("code",login);
        return jsonObject;
    }

    @RequestMapping("/checkLoginName")
    @ResponseBody
    JSONObject checkLoginName(String loginName){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isExist", "0");
        if (loginName != null && loginName.trim().length() != 0){
            User user = new User();
            user.setLoginName(loginName);
            List<User> user1 = userMapper.select(user);
            if ( user1 != null && user1.size() > 0){
                jsonObject.put("isExist", "1");
            }
        }
        return jsonObject;
    }

}
