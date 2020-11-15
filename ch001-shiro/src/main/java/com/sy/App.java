package com.sy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //1.创建安全管理器对象 ctrl+h
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.给安全管理器设置我们的realm  去找数据
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        //3.SecurityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);

        //4.认证的流程中 关键对象 subject主体
        Subject subject = SecurityUtils.getSubject();

        //5.创建令盘
        UsernamePasswordToken token = new UsernamePasswordToken("lisi","12345");
        try{
            System.out.println("认证状态"+subject.isAuthenticated());
            subject.login(token); //用户认证
            System.out.println("认证状态"+subject.isAuthenticated());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
