package com.aurora.version.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author panda00hi
 * @date 2023.09.19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "aurora.monitor.version")
public class AuroraVersionMonitorProperties {
}
