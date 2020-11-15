package com.sy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerMd5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();

        //根据用户名查询数据库
        if("lisi".equals(principal)){
            return new SimpleAuthenticationInfo(principal,"9b8457c900297e13fcd974e7b18551ee",
                    ByteSource.Util.bytes("hfjrh"),this.getName());
        }
        return null;
    }
}
