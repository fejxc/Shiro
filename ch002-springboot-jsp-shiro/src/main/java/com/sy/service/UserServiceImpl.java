package com.sy.service;

import com.sy.dao.UserDAO;
import com.sy.entity.User;
import com.sy.utils.SAltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDAO userDAO;
    @Override
    public void register(User user) {
        // 处理业务
        //生成随机盐
        String salt = SAltUtils.getSalt(8);
        //将随机盐保存到数据
        user.setSalt(salt);
        //明文密码进行md5 salt hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDAO.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }
}
