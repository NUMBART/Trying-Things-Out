package com.pandey.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistributedRateLimiter implements Runnable {
    @Autowired
    RedisTemplate<String, Integer> redisTemplate;
    @Override
    public void run() {
        // Fetch used token count
        String key = "key";
        SessionCallback<Boolean> sessionCallback = new SessionCallback<>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                operations.watch(key);
                Integer usedTokenCount = (Integer) operations.opsForValue().get(key);
                System.out.println("key value in " + Thread.currentThread().getName() + " before write: " + usedTokenCount);
                if(usedTokenCount >= 3) {
                    System.out.println("You have exceeded the number of allowed operations!");
                    return false;
                }
                long randomSleepTimes = (long) (Math.random() * 1001) + 700;
                try {
                    Thread.sleep(randomSleepTimes);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                operations.multi();
                operations.opsForValue().increment(key);
                List<Integer> transaction = operations.exec();
                if(transaction == null || transaction.size() == 0) {
                    // should be retrying here instead as others might have failed simply due to
                    // a different transaction proceeding despite tokens being left
                    System.out.println("You have exceeded the number of allowed operations!");
                    return false;
                }
                return true;
            }
        };
        if(redisTemplate.execute(sessionCallback))
            System.out.println("Request allowed in " + Thread.currentThread().getName() + " forwarding request to service..");
        else
            System.out.println("Request rate-limited in " + Thread.currentThread().getName() + " discarding request..");
    }
}

