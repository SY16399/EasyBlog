package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.pojo.MyUser;
import org.example.tools.RedisUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Autowired
    RedisUntil redisUntil;
    public String getToken(MyUser user){
        String token = "";
        //只有一个小时时间
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60*60*1000;
        Date end = new Date(currentTime);
        token = JWT.create()
                .withAudience(String.valueOf(user.getId()))
                .withIssuedAt(start)//开始时间
                .withExpiresAt(end)//过期时间
                .sign(Algorithm.HMAC256(user.getPassword() + "MText!76&sQ^"));
        //redis 存储 token
        redisUntil.set("user_id:"+user.getName(),token);
        redisUntil.expire("user_id:"+user.getName(),60*60*1000);
        return token;

    }
}
