package com.aurora.config.starter;

import com.aurora.config.core.ConfigFacade;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author panda00hi
 * @date 2023.09.28
 */
@Configuration
@EnableConfigurationProperties(AuroraConfigProperties.class)
public class AuroraConfigAutoConfiguration {

    @Resource
    AuroraConfigProperties auroraConfigProperties;


    @Bean
    @ConditionalOnMissingBean(ConfigFacade.class)
    public ConfigFacade configFacade() {
        return new ConfigFacade();
    }


}
