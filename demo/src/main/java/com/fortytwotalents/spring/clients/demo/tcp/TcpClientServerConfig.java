package com.fortytwotalents.spring.clients.demo.tcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;

@Slf4j
@Configuration
public class TcpClientServerConfig {

    @Bean
    public IntegrationFlow tcpServer() {
        return IntegrationFlow.from(Tcp.inboundGateway(
                Tcp.netServer(1234)
                        .serializer(codec()) // default is CRLF
                        .deserializer(codec()))) // default is CRLF
                .transform(Transformers.objectToString()) // byte[] -> String
                .<String, String>transform(p -> "42")
                .get();
    }

    @Bean
    public IntegrationFlow tcpClient() {
        return IntegrationFlow.from(DeepThoughtComputer.class)
                .handle(Tcp.outboundGateway(
                        Tcp.netClient("localhost", 1234)
                                .serializer(codec()) // default is CRLF
                                .deserializer(codec()))) // default is CRLF
                .transform(Transformers.objectToString()) // byte[] -> String
                .get();
    }

    @Bean
    public AbstractByteArraySerializer codec() {
        return TcpCodecs.lf();
    }

    // @Bean
    @DependsOn("tcpClient")
    ApplicationRunner tcpRunner(DeepThoughtComputer computer) {
        return args -> {
            String answerToLife = "the answer to life the universe and everything";
            log.error("Sending value `{}` via TCP an got calculated result `{}`.",
                    answerToLife,
                    computer.calculate(answerToLife));
        };
    }

    public interface DeepThoughtComputer {

        String calculate(String query);

    }
}
