package com.sy.dao;
import com.sy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    void save (User user);

    User findByUserName(String username);

    //查询用户角色
    User findRolesByUserName(String username);
}
