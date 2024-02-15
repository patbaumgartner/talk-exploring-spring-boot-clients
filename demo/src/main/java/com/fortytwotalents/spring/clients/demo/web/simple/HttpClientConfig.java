package com.fortytwotalents.spring.clients.demo.web.simple;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionException;

@Slf4j
@Configuration
public class HttpClientConfig {

    // @Bean
    ApplicationRunner httpClientApplicationRunner(ObjectMapper objectMapper) {
        return args -> {
            String restApi = "http://localhost:8080/api";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(restApi))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            Event[] events = objectMapper.readValue(response.body(), Event[].class);

            log.error("Received a list of events from the API at `{}`.", restApi);
            Arrays.asList(events).forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    // @Bean
    ApplicationRunner httpClientAsyncApplicationRunner(ObjectMapper objectMapper) {
        return args -> {
            String restApi = "http://localhost:8080/api";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(restApi))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            List<Event> events = client.sendAsync(request, BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(content -> {
                        try {
                            return objectMapper.readValue(content, new TypeReference<List<Event>>() {
                            });
                        } catch (IOException ioe) {
                            throw new CompletionException(ioe);
                        }
                    })
                    .get();

            log.error("Received a list of events from the API at `{}`.", restApi);
            events.forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

}
