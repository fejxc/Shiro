package com.sy.service;

import com.sy.entity.Perms;
import com.sy.entity.User;

import java.util.List;


public interface UserService {
    //注册
    void register(User user);
    //根据用户名查询用户
    User findByUserName(String username);
    //查询用户角色
    User findRolesByUserName(String username);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
