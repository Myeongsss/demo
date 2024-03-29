package com.api.demo.common.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "api")
public class CoreProperties {

    private String kakaoUrl;
    private String kakaoAuthKey;
    private String naverUrl;

}
