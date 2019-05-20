package com.yougou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yougou.mapper.UserMapper;
import com.yougou.pojo.User;
import com.yougou.service.IUserService;
import com.yougou.util.RedisUtils;
import com.yougou.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private MailService mailService;

    @Resource
    private RedisUtils redisUtils;

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

    @Override
    public JSONObject sendEmail(String mail) {
        JSONObject jsonObject = new JSONObject();
        if (mail != null && mail.trim().length() >0){
            User user = new User();
            user.setMail(mail);
            List<User> list = getUser(user);
            if (list == null || list.size() == 0){
                jsonObject.put("code", 201);
                jsonObject.put("msg", "邮箱未注册");
                return jsonObject;
            }
            try {
                String code = StringUtil.getRandomStr(6);
                mailService.sendSimpleMail(mail, "呐呐学习系统验证码", code);
               redisUtils.set(mail, code);
               jsonObject.put("code", 200);
               jsonObject.put("msg", "邮箱发送成功，请查收");
            } catch (Exception e) {
                jsonObject.put("code", 202);
                jsonObject.put("msg", "邮箱发送失败，请联系管理员");
                return jsonObject;
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject verifyEmailCode(String mail, String code) {
        JSONObject jsonObject = new JSONObject();
        if (mail != null && mail.trim().length() >0 && code != null && code.trim().length() > 0){
            User user = new User();
            user.setMail(mail);
            List<User> list = getUser(user);
            if (list == null || list.size() == 0){
                jsonObject.put("code", 201);
                jsonObject.put("msg", "邮箱未注册");
                return jsonObject;
            }

            Object o = redisUtils.get(mail);
            if (o == null || o.toString().length() == 0){
                jsonObject.put("code", 202);
                jsonObject.put("msg", "无效验证码，请重新获取");
                return jsonObject;
            }

            String codeStr = o.toString();
            if (!codeStr.equals(code)){
                jsonObject.put("code", 203);
                jsonObject.put("msg", "验证码不正确");
                return jsonObject;
            }

            jsonObject.put("code", 200);
            jsonObject.put("msg", "验证通过");
            redisUtils.del(mail);
        }
            return jsonObject;
    }

    @Override
    public JSONObject changePwd(String mail, String newPwd) {
        JSONObject jsonObject = new JSONObject();
        if (mail != null && mail.trim().length() >0 && newPwd != null && newPwd.trim().length() > 0) {
            User user = new User();
            user.setMail(mail);
            List<User> list = getUser(user);
            if (list == null || list.size() == 0) {
                jsonObject.put("code", 201);
                jsonObject.put("msg", "邮箱未注册");
                return jsonObject;
            }

            User orgUser = list.get(0);
            orgUser.setPwd(newPwd);
            userMapper.updateByPrimaryKey(orgUser);
            jsonObject.put("code", 200);
            jsonObject.put("msg", "密码修改成功");
        }
        return jsonObject;
    }

}
