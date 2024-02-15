package com.fortytwotalents.spring.clients.demo.data.proxy.jpa;

import com.fortytwotalents.spring.clients.demo.data.SimpleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class JpaConfig {

    // @Bean
    ApplicationRunner eventJpaRepositoryApplicationRunner(JpaEventRepository jpaEventRepository) {
        return args -> {
            String queryName = "Workshop";

            List<EventEntity> events = jpaEventRepository.findByNameContains(queryName);
            events.forEach(e -> log.error("Events for query {} with data `{}` found.", queryName, e));

            List<SimpleEvent> simpleEvents = jpaEventRepository.findByCustomQuery(queryName);
            simpleEvents.forEach(e -> log.error("Events for query {} with data `{}` found.", queryName, e));
        };
    }
}
