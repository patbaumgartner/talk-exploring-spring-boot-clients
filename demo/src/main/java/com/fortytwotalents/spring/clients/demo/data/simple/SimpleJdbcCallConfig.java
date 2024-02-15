package com.fortytwotalents.spring.clients.demo.data.simple;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleJdbcCallConfig {

    // @Bean
    ApplicationRunner simpleJdbcCallApplicationRunner(SimpleJdbcCallEventRepository simpleJdbcCallEventRepository) {
        return args -> {
            try {
                Event event = simpleJdbcCallEventRepository.findById(99L);
                log.error("Event with data `{}` found.", event);
            } catch (Exception e) {
                // ORA-01403: no data found, or any java.sql.SQLException
                log.warn(e.getMessage());
            }
        };
    }
}
