package com.fortytwotalents.spring.clients.demo.web.proxy;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Slf4j
@Configuration
public class HttpInterfaceWebClientConfig {

    @Bean
    public HttpInterfaceWebClient httpInterfaceWebClient() {
        String restApi = "http://localhost:8080/api";
        WebClient webClient = WebClient.create(restApi);

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return factory.createClient(HttpInterfaceWebClient.class);
    }

    // @Bean
    ApplicationRunner httpInterfaceWebClientApplicationRunner(HttpInterfaceWebClient httpInterfaceWebClient) {
        return args -> {
            List<Event> events = httpInterfaceWebClient.fetchAllEvents();
            events.forEach(e -> log.error("Entry with data `{}`", e));
        };
    }

    public interface HttpInterfaceWebClient {

        @GetExchange
        List<Event> fetchAllEvents();
    }

}
