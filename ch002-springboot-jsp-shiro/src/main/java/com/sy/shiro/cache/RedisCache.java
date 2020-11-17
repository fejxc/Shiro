package com.sy.shiro.cache;

import com.sy.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;
//自定义redis缓存实现
public class RedisCache<k,v> implements Cache<k,v> {
    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    @Override
    public v get(k k) throws CacheException {
        System.out.println("get  key-->"+k);
        return (v) getRedisTemplate().opsForValue().get(k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put  key-->"+k);
        System.out.println("put  value-->"+v);
        getRedisTemplate().opsForValue().set(k.toString(),v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
    //封装获取 RedisTemplate的方法
    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());//对象v是String类型 做String类型序列化
        return redisTemplate;
    }
}
