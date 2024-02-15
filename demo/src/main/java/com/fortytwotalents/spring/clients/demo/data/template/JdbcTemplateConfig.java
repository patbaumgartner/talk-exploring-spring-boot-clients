package com.fortytwotalents.spring.clients.demo.data.template;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class JdbcTemplateConfig {

    // @Bean
    ApplicationRunner jdbcTemplateRepositoryApplicationRunner(JdbcTemplateEventRepository jdbcTemplateEventRepository) {
        return args -> {
            String queryName = "Technology";
            List<Event> event = jdbcTemplateEventRepository.findByNameLike(queryName);
            log.error("Events for query {} with data `{}` found.", queryName, event);
        };
    }

}
