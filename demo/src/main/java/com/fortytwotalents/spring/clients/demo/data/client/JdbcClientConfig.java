package com.fortytwotalents.spring.clients.demo.data.client;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class JdbcClientConfig {

    // @Bean
    ApplicationRunner jdbcClientRepositoryApplicationRunner(JdbcClientEventRepository jdbcClientEventRepository) {
        return args -> {
            String queryName = "Technology";
            List<Event> event = jdbcClientEventRepository.findByNameLikeMappedClass(queryName);
            log.error("Events for query {} with data `{}` found.", queryName, event);
        };
    }
}
