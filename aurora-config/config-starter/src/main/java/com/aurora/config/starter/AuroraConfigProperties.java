package com.aurora.config.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author panda00hi
 * @date 2023.09.28
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "aurora.monitor.config")
public class AuroraConfigProperties {
}
