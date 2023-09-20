package com.aurora.dtblock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author panda00hi
 * @date 2023.08.29
 */
@ConfigurationProperties(prefix = "redis.lock")
public class RedisLockProperties {

    /**
     * 默认锁过期时间为 30 秒
     */
    private long expireTime = 30000L;

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
