package org.example.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUntil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            System.out.println("没抓");
            e.printStackTrace();
        }
        return result;
    }
    //获取值并设置新值
    public String getAndSet(final String key, String value) {
        String result = "false";
        try {
            result =  redisTemplate.opsForValue().getAndSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(final String key){
        boolean result = false;
        try{
            redisTemplate.delete(key);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean hasKey(String key){
        boolean result = false;
        try{
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean expire(String key,long time){
        boolean result = false;
        try{
            return Boolean.TRUE.equals(redisTemplate.expire(key, time, TimeUnit.SECONDS));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
