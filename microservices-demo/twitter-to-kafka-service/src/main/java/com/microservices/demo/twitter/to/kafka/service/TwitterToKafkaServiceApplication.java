package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.init.StreamInitializer;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo") // find in other modules
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
        // Export bearer token from console as it is referenced from application.yml

        // Logging
        private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

        private final StreamRunner streamRunner;

        private final StreamInitializer streamInitializer;

        // constructor injection: allows immutable objects and does not use reflection like autowired as it runs slower
        public TwitterToKafkaServiceApplication(StreamRunner runner, StreamInitializer streamInitializer) {
                this.streamRunner = runner;
                this.streamInitializer = streamInitializer;
        }

        public static void main(String[] args){
                SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
        }

        // Initialisation logic : can also be written using other ways, PostConstructor and ApplicationListener. This is CommandListListener.
        @Override
        public void run(String... args) throws Exception {
                LOG.info("App starts...");
                streamInitializer.init();
                streamRunner.start();
        }
}

/**
 * What are the benefits of using constructor injection over field injection? Select all that apply.
 *
 *    a.) Prevents using reflection
 *
 *    b.) Forces the object to be created with the injected parameter
 *
 *    c.) Lets to do the injection without using @Autowired annotation
 *
 *    d.) Lets the injected field to be defines as final so that the it favours immutability
 */