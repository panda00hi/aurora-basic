package com.aurora.version.starter;

import com.aurora.version.core.VersionFacade;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author panda00hi
 * @date 2023.09.19
 */
@Configuration
@EnableConfigurationProperties(AuroraVersionMonitorProperties.class)
public class AuroraVersionMonitorAutoConfiguration {

    @Getter
    @Resource
    AuroraVersionMonitorProperties auroraVersionMonitorProperties;

    @Bean
    @ConditionalOnMissingBean(VersionFacade.class)
    public VersionFacade versionFacade() {
        return new VersionFacade();
    }


}
