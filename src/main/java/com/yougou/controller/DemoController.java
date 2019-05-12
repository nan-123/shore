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

@Controller
@RequestMapping(value = "/demo")
@CrossOrigin
public class DemoController {

    @Resource
    private IUserService userService;

    @Resource
    private RedisUtils redisUtils;


    @Resource
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/hello")
    String hello() {
        return "hello";
    }

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

    @ResponseBody
    @RequestMapping("/testRedis")
    String testRedis(){
        boolean set = redisUtils.set("test", "test");
        String test = null;
        if (set){
            test = redisUtils.get("test").toString();

        }
        return test;
    }

//    @ResponseBody
//    @RequestMapping("/testDubbo")
//    ChainSellerApp testDubbo(){
//        ChainSellerApp chainSellerApp = null;
//        try {
//            chainSellerApp = chainSellerAppService.getChainSellerApp("JL-001");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return chainSellerApp;
//    }

/**
 * 增
 */
    @ResponseBody
    @RequestMapping("/insert")
     void insert(){
         User user = new User();
         user.setId("2");
         user.setAge("14");
         user.setName("nan");
         userMapper.insert(user);
     }

    @ResponseBody
    @RequestMapping("/del")
     void del(){
        userMapper.deleteByPrimaryKey("2");
     }

    @ResponseBody
    @RequestMapping("/update")
    void update(){
        User user = new User();
        user.setId("2");
        user.setName("nananana");
        userMapper.updateByPrimaryKey(user);
    }

    @RequestMapping("/login")
    void login(String name, String pwd){
        System.out.println("账号："+ name);
        System.out.println("密码" + pwd);

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


}
