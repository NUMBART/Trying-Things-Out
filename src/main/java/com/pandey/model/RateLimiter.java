package com.pandey.model;

import com.pandey.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RateLimiter implements Runnable {
    @Autowired
    RedisTemplate<String, Integer> redisTemplate;
    @Override
    public void run() {
        // Fetch used token count
        Integer value = redisTemplate.opsForValue().get("key");
        System.out.println("key value in " + Thread.currentThread().getName() + " before write: " + value);
        // check whether anymore tokens remaining
        if(value >= 3) {
            System.out.println("You have exceeded the number of allowed operations!");
            return;
        }
        // adding a bit more randomness to thread behavior between read and write
        try {
            long randomSleepTimes = (long) (Math.random() * 1001) + 700;
            Thread.sleep(randomSleepTimes);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // increment token used
        redisTemplate.opsForValue().increment("key");
        // logging token values and allowing operations
        System.out.println("key value in " + Thread.currentThread().getName() + " after write: " + redisTemplate.opsForValue().get("key"));
    }
}

