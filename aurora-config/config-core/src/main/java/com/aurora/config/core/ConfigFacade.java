package com.aurora.config.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panda00hi
 * @date 2023.09.27
 */

@RestController
@RequestMapping("/dev/v1")
public class ConfigFacade {

    @Autowired
    private Environment environment;

    public ConfigFacade() {
    }

    @GetMapping("/env")
    public Object getCurrentConfig(@RequestParam(required = false) String key) {

        if (key != null) {
            return environment.getProperty(key);
        }
        return environment.getActiveProfiles();
    }



}
