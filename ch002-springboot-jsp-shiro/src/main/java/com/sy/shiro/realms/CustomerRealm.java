package com.sy.shiro.realms;

import com.sy.entity.Perms;
import com.sy.entity.Role;
import com.sy.entity.User;
import com.sy.service.UserService;
import com.sy.utils.ApplicationContextUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证" +primaryPrincipal);
        //根据主身份信息获取角色信息和资源的权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findRolesByUserName(primaryPrincipal);
        //赋值授权角色
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());

                //权限信息
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perms1 -> {
                        simpleAuthorizationInfo.addStringPermission(perms1.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("*************");
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());

        }
        return null;
    }
}
