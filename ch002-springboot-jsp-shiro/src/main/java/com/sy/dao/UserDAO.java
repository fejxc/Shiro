package com.sy.dao;
import com.sy.entity.Perms;
import com.sy.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    void save (User user);

    User findByUserName(String username);

    //查询用户角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId(String id);
}
