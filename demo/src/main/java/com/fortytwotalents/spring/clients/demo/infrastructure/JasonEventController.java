package com.fortytwotalents.spring.clients.demo.infrastructure;

import com.fortytwotalents.spring.clients.demo.data.proxy.jpa.EventEntity;
import com.fortytwotalents.spring.clients.demo.data.proxy.jpa.JpaEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JasonEventController {

    private final JpaEventRepository jpaEventRepository;

    @RequestMapping("/api")
    public List<EventEntity> listAllEvents() {
        return jpaEventRepository.findAll();
    }
}
