package com.sy;

import com.sy.realm.CustomerMd5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

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

        //认证用户授权
        if(subject.isAuthenticated()){

            //1.基于角色控制权限
            System.out.println(subject.hasRole("admin"));
//            System.out.println(subject.hasRole("user"));
//            System.out.println(subject.hasRole("super"));

            //基于多角色权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));

            //是否具有其中的一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin","user","super"));
            for (boolean a:booleans    //将数据库查询的角色信息赋值给权限对象
                 ) {
                System.out.println(a);
            }

            System.out.println("***************************");
            //基于权限字符串的访问控制  资源标识符:操作:资源类型
            System.out.println("权限"+subject.isPermitted("user:update:01"));
            System.out.println("权限"+subject.isPermitted("product:create"));
            
            //分别具有哪些权限 (.var iter )
            boolean[] permitted = subject.isPermitted("user:*:01", "order:*:01");
            for (boolean b : permitted) {
                System.out.println(b);
            }
            
            //同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("user:*:01", "product:create:110");
            System.out.println(permittedAll);
        }

    }
}
