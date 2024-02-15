package com.fortytwotalents.spring.clients.demo.web.client;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class WebClientConfig {

    // @Bean
    ApplicationRunner webClientAutoConfigApplicationRunner(WebClient.Builder webClientBuilder) {
        return args -> {

            WebClient webClient = webClientBuilder.build();
            String restApi = "http://localhost:8080/api";

            Flux<Event> events = webClient
                    .get()
                    .uri(restApi)
                    .retrieve()
                    .bodyToFlux(Event.class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            events.doOnNext(e -> {
                log.error("Entry with data `{}`", e);
            }).blockLast();
        };
    }

    // @Bean
    ApplicationRunner webClientApplicationRunner() {
        return args -> {

            WebClient webClient = WebClient.create();
            String restApi = "http://localhost:8080/api";

            Flux<Event> events = webClient
                    .get()
                    .uri(restApi)
                    .retrieve()
                    .bodyToFlux(Event.class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            events.doOnNext(e -> {
                log.error("Entry with data `{}`", e);
            }).blockLast();
        };
    }
}
