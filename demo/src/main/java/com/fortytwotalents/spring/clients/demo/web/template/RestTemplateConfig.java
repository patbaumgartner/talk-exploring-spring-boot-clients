package com.fortytwotalents.spring.clients.demo.web.template;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {

    // @Bean
    ApplicationRunner restTemplateApplicationRunner(RestTemplateBuilder restTemplateBuilder) {
        return args -> {
            RestTemplate restTemplate = restTemplateBuilder.build();
            String restApi = "http://localhost:8080/api";

            Event[] events = restTemplate.getForObject(restApi, Event[].class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            Arrays.asList(events).forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    // @Bean
    ApplicationRunner restTemplateWithTypesApplicationRunner(RestTemplateBuilder restTemplateBuilder) {
        return args -> {
            RestTemplate restTemplate = restTemplateBuilder.build();
            String restApi = "http://localhost:8080/api";

            ResponseEntity<List<Event>> events = restTemplate.exchange(restApi, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Event>>() {
                    });

            log.error("Received a list of events from the API at `{}`.", restApi);
            Arrays.asList(events.getBody()).forEach(e -> log.error("Entry with data `{}`", e));
        };
    }
}
