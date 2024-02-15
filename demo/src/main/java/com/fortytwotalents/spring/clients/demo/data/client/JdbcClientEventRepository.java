package com.fortytwotalents.spring.clients.demo.data.client;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcClientEventRepository {

    private final JdbcClient jdbcClient;

    public List<Event> findByNameLikeMappedClass(String name) {
        return jdbcClient.sql("SELECT * FROM event WHERE name LIKE :name")
                .param("name", "%" + name + "%")
                .query(Event.class)
                .list();
    }

    public List<Event> findByNameLike(String name) {
        return jdbcClient.sql("SELECT * FROM event WHERE name LIKE :name")
                .param("%" + name + "%")
                .query((rs, rowNum) -> new Event(rs.getLong("ID"),
                        rs.getTimestamp("EVENT_DATE").toLocalDateTime(),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION")))
                .list();
    }
}
