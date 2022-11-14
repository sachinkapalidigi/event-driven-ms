package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "retry-config")
public class RetryConfigData {
    // These will be present in application.yml file in the main twitter-to-kafka-service
    private Long initialIntervalMs;
    private Long maxIntervalMs;
    private Double multiplier;
    private Integer maxAttempts;
    private Long sleepTimeMs;
}
