package com.fortytwotalents.spring.clients.demo.web.client;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
public class RestClientConfig {

    // @Bean
    ApplicationRunner restClientAutoConfigApplicationRunner(RestClient.Builder restClientBuilder) {
        return args -> {
            RestClient restClient = restClientBuilder.build();

            String restApi = "http://localhost:8080/api";

            Event[] events = restClient
                    .get()
                    .uri(restApi)
                    .retrieve()
                    .body(Event[].class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            Arrays.asList(events).forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    // @Bean
    ApplicationRunner restClientApplicationRunner() {
        return args -> {

            RestClient restClient = RestClient.create();
            String restApi = "http://localhost:8080/api";

            Event[] events = restClient
                    .get()
                    .uri(restApi)
                    .retrieve()
                    .body(Event[].class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            Arrays.asList(events).forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    // @Bean
    ApplicationRunner restClientWithTypesApplicationRunner(RestTemplateBuilder restTemplateBuilder) {
        return args -> {

            RestTemplate restTemplate = restTemplateBuilder.build();
            String restApi = "http://localhost:8080/api";
            RestClient restClient = RestClient
                    .builder(restTemplate)
                    .baseUrl(restApi)
                    .build();

            List<Event> events = restClient
                    .get()
                    .uri(restApi)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Event>>() {
                    });

            log.error("Received a list of events from the API at `{}`.", restApi);
            events.forEach(e -> log.error("Entry with data `{}`", e));
        };
    }
}
