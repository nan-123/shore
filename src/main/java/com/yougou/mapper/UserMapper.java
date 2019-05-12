package com.yougou.mapper;

import com.yougou.pojo.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> getUser(User user);

    Integer getUserCount(User user);
}
