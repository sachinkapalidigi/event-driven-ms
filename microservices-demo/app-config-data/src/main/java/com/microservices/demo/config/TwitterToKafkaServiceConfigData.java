package com.microservices.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Data // For getter setter methods
@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {
    private ArrayList<String> twitterKeywords;
    private String welcomeMessage;
    private String twitterV2BaseUrl;
    private String twitterV2RulesBaseUrl;
    private String twitterV2BearerToken;
    private Boolean enableMockTweets;
    private Long mockSleepMs;
    private Integer mockMinTweetLength;
    private Integer mockMaxTweetLength;
}
