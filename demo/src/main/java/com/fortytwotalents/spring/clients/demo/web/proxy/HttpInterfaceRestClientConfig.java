package com.fortytwotalents.spring.clients.demo.web.proxy;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Slf4j
@Configuration
public class HttpInterfaceRestClientConfig {

    @Bean
    public HttpInterfaceRestClient httpInterfaceRestClient() {
        String restApi = "http://localhost:8080/api";
        RestClient restClient = RestClient.create(restApi);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();

        return factory.createClient(HttpInterfaceRestClient.class);
    }

    // @Bean
    ApplicationRunner httpInterfaceRestClientApplicationRunner(HttpInterfaceRestClient httpInterfaceRestClient) {
        return args -> {
            List<Event> events = httpInterfaceRestClient.fetchAllEvents();
            events.forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    public interface HttpInterfaceRestClient {

        @GetExchange
        List<Event> fetchAllEvents();
    }

}
