package com.aurora.version.core;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author panda00hi
 * @date 2023.09.19
 */
@RestController
@RequestMapping("/dev/v1")
public class VersionFacade {
    private static final Logger log = LoggerFactory.getLogger(VersionFacade.class);

    private static volatile String serverVersion;

    @PostConstruct
    public void init() {
        try {
            ClassPathResource res = new ClassPathResource("version.txt");
            serverVersion = new BufferedReader(new InputStreamReader(res.getInputStream())).lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            Tags tags = Tags.of(Tag.of("serverVersion", serverVersion));
            String springVersion = SpringVersion.getVersion();
            if (Objects.nonNull(springVersion)) {
                tags = tags.and(Tags.of("springVersion", springVersion));
            }
            String springBootVersion = SpringBootVersion.getVersion();
            tags = tags.and(Tag.of("springBootVersion", springBootVersion));
            Metrics.counter("server_info", tags).increment();
        } catch (IOException e) {
            log.error("init version failed!", e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/version")
    public String getVersion() {
        return serverVersion;
    }

}
