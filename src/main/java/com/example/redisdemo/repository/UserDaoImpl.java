package com.example.redisdemo.repository;

import com.example.redisdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="USER";
    @Override
    public boolean saveUser(User user) {
       try{
           redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
           redisTemplate.expire(KEY,60,TimeUnit.SECONDS);
            return true;
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users=redisTemplate.opsForHash().values(KEY);
        return users;
    }


}
