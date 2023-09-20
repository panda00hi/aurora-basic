package com.aurora.dtblock.config;


import com.aurora.dtblock.RedisLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@ConditionalOnClass(RedisTemplate.class)
@EnableConfigurationProperties(RedisLockProperties.class)
public class RedisLockAutoConfiguration {
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisLockProperties redisLockProperties;

    public RedisLockAutoConfiguration(RedisTemplate<String, String> redisTemplate, RedisLockProperties redisLockProperties) {
        this.redisTemplate = redisTemplate;
        this.redisLockProperties = redisLockProperties;
    }

    @Bean
    @ConditionalOnMissingBean(RedisLock.class)
    public RedisLock redisLock() {
        return new RedisLock(redisTemplate);
    }
}