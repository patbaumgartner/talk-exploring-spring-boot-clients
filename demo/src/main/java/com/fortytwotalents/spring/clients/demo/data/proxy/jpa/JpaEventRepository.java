package com.fortytwotalents.spring.clients.demo.data.proxy.jpa;

import com.fortytwotalents.spring.clients.demo.data.SimpleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaEventRepository extends JpaRepository<EventEntity, Long> {

    List<EventEntity> findByNameContains(String name);

    List<EventEntity> findByEventDateIsBetween(LocalDateTime begin, LocalDateTime end);

    @Query("SELECT new com.fortytwotalents.spring.clients.demo.data.SimpleEvent" +
            "(e.eventDate, e.name, e.description) FROM EventEntity e " +
            "WHERE e.name LIKE %:name%")
    List<SimpleEvent> findByCustomQuery(@Param("name") String name);
}
