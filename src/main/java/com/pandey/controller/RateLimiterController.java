package com.pandey.controller;

import com.pandey.model.DistributedRateLimiter;
import com.pandey.model.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Tried to compare performing atomic operations in redis
to simulate a rate limiter.
1) First version would do it without using multi and fail
2) second version would do it with using multi and successfully block requests

Done
Implemented first and second version, however multi seems a bit too aggressive and not allowing any writes
- problem was accessing older rate-limited value instead of accessing the result of the transaction
Problem is even certain allowed requests are failing due to one atomic increment at a time
- need to retry again after some time unless explicitly fail from being rate limited
 */

@RestController
public class RateLimiterController {
    @Autowired
    RedisTemplate<String, Integer> redisTemplate;
    @Autowired
    RateLimiter rateLimiter;
    @Autowired
    DistributedRateLimiter distributedRateLimiter;
    @GetMapping("/")
    public String getHelloMessage() {
        redisTemplate.opsForValue().set("key", 1);
        for(int i = 0; i < 5; ++i) {
            Thread curRateLimiter = new Thread(rateLimiter);
            curRateLimiter.start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForValue().set("key", 1);
        System.out.println("\n\n");
        for(int i = 0; i < 5; ++i) {
            Thread curRateLimiter = new Thread(distributedRateLimiter);
            curRateLimiter.start();
        }
        System.out.println("\n\n");
        return "Hello";
    }
}
