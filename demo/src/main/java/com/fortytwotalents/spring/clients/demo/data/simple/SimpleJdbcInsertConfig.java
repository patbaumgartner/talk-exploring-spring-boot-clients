package com.fortytwotalents.spring.clients.demo.data.simple;

import com.fortytwotalents.spring.clients.demo.data.Event;
import com.fortytwotalents.spring.clients.demo.data.SimpleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Configuration
public class SimpleJdbcInsertConfig {

    // @Bean
    ApplicationRunner simpleJdbcInsertApplicationRunner(
            SimpleJdbcInsertEventRepository simpleJdbcInsertEventRepository) {
        return args -> {
            SimpleEvent event1 = new SimpleEvent(LocalDateTime.of(LocalDate.of(2024, 1, 19), LocalTime.MIN),
                    "VoxxedDays Ticino", "From Developers for Developers");
            simpleJdbcInsertEventRepository.addEvent(event1);
            log.error("Event with data `{}` stored.", event1);

            Event event2 = new Event(LocalDateTime.of(LocalDate.of(2024, 1, 22), LocalTime.MIN),
                    "VoxxedDays Cern",
                    "From Developers for Developers");
            simpleJdbcInsertEventRepository.addEventWithKeyHolder(event2);
            log.error("Event with data `{}` stored.", event2);

            Event event3 = new Event(LocalDateTime.of(LocalDate.of(2024, 3, 7), LocalTime.MIN),
                    "VoxxedDays Zurich",
                    "From Developers for Developers");
            simpleJdbcInsertEventRepository.addEventWithKey(event2);
            log.error("Event with data `{}` stored.", event2);
        };
    }

}
