package com.fortytwotalents.spring.clients.demo.data.template;


import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateEventRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Event> findByNameLike(String name) {
        return jdbcTemplate.query("SELECT * FROM event WHERE name LIKE ?",
                (rs, rowNum) -> new Event(
                        rs.getLong("ID"),
                        rs.getTimestamp("EVENT_DATE").toLocalDateTime(),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION")),
                "%" + name + "%");
    }
}
