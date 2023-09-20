package com.aurora.dtblock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisLock {
    private static final String LOCK_PREFIX = "redis_lock:";
    private static final long DEFAULT_WAIT_TIME = 500L;
    private static final int DEFAULT_RETRY_TIMES = 3;

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<Long> lockScript;
    private final RedisScript<Long> unlockScript;

    public RedisLock(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.lockScript = new DefaultRedisScript<>(
                "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end",
                Long.class
        );
        this.unlockScript = new DefaultRedisScript<>(
                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
                Long.class
        );
    }

    public boolean tryLock(String lockKey, long expireTime) {
        String requestId = UUID.randomUUID().toString();
        Boolean acquired = false;
        int retryTimes = 0;
        while (!acquired && retryTimes < DEFAULT_RETRY_TIMES) {
            acquired = redisTemplate.execute(
                    lockScript,
                    Collections.singletonList(LOCK_PREFIX + lockKey),
                    requestId,
                    String.valueOf(expireTime)
            ) == 1;
            if (!acquired) {
                try {
                    TimeUnit.MILLISECONDS.sleep(DEFAULT_WAIT_TIME);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            retryTimes++;
        }
        return acquired;
    }

    public boolean tryLock(String lockKey) {
        return tryLock(lockKey, redisTemplate.getRequiredConnectionFactory().getConnection().time());
    }

    public void unlock(String lockKey) {
        redisTemplate.execute(unlockScript, Collections.singletonList(LOCK_PREFIX + lockKey), lockKey);
    }
}