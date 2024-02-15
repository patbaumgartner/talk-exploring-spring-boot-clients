package com.fortytwotalents.spring.clients.demo.data.simple;

import com.fortytwotalents.spring.clients.demo.data.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcCallOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SimpleJdbcCallEventRepository {

    private final JdbcTemplate jdbcTemplate;

    public Event findById(Long id) {
        SimpleJdbcCallOperations simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

        simpleJdbcCall.withProcedureName("GET_EVENT");

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", id);

        Map<String, Object> out = simpleJdbcCall.execute(in);

        Event event = new Event();
        event.setId((Long) out.get("O_ID"));
        event.setEventDate((LocalDateTime) out.get("O_EVENT_DATE"));
        event.setName((String) out.get("O_NAME"));
        event.setDescription((String) out.get("O_DESCRIPTION"));

        log.error("Entry with data {} retrieved.", event);

        return event;
    }
}
