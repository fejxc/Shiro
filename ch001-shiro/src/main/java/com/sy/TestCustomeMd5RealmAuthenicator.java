package com.sy;

import com.sy.realm.CustomerMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

public class TestCustomeMd5RealmAuthenicator {
    public static void main(String[] args) {
        //创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
       CustomerMd5Realm realm = new CustomerMd5Realm();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
       realm.setCredentialsMatcher(credentialsMatcher);
        //注入realm
        defaultSecurityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //认证
        UsernamePasswordToken token = new UsernamePasswordToken("lisi","12345");
        try{
            subject.login(token);
            System.out.println("登陆成功");
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("无效用户名");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }

    }
}
