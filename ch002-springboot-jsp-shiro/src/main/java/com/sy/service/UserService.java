package com.sy.service;

import com.sy.entity.User;

public interface UserService {
//    注册
    void register(User user);

    //根据用户名查询用户
    User findByUserName(String username);
}
