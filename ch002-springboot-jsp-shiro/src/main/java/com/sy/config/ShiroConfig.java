package com.sy.config;

import com.sy.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

/**
 * 用来整个shiro的相关配置
 */
@Configuration
public class ShiroConfig {

    //1 创建shiroFilter  负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        HashMap<String, String> map = new HashMap<>();
        map.put("/user/login","anon");  //不受限的资源顺序放在前面
        map.put("/user/register","anon");  //不受限的资源顺序放在前面
        map.put("/register.jsp","anon");  //不受限的资源顺序放在前面
        map.put("/**","authc"); //authc 请求这个资源需要认证授权   anon匿名访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //默认认证界面路径
//        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        //配置系统公共资源
        return shiroFilterFactoryBean;
    }
    //2 创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3 创建自定义realm
    @Bean
    public Realm realm(){
        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}
