package com.sy.shiro.realms;

import com.sy.entity.User;
import com.sy.service.UserService;
import com.sy.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("*************");
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        System.out.println(userService);
        User user = userService.findByUserName(principal);
//        if("lisi".equals(principal)){
//            return new SimpleAuthenticationInfo(principal,"12345",this.getName());
//        }
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());

        }
        return null;
    }
}
