package com.fortytwotalents.spring.clients.demo.data.simple;

import com.fortytwotalents.spring.clients.demo.data.Event;
import com.fortytwotalents.spring.clients.demo.data.SimpleEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SimpleJdbcInsertEventRepository {

    private final JdbcTemplate jdbcTemplate;

    public void addEvent(SimpleEvent e) {
        SimpleJdbcInsertOperations simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(e);

        int successful = simpleJdbcInsert.withTableName("event")
                .usingGeneratedKeyColumns("id")
                .execute(parameters);

        log.error("Entry with successful {} created.", successful > 0);
    }

    public void addEventWithKeyHolder(Event e) {
        SimpleJdbcInsertOperations simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> params = new HashMap<>();
        params.put("event_date", e.getEventDate());
        params.put("name", e.getName());
        params.put("description", e.getDescription());

        KeyHolder keyHolder = simpleJdbcInsert
                .withTableName("event")
                .usingColumns("event_date", "name", "description")
                .usingGeneratedKeyColumns("id")
                .withoutTableColumnMetaDataAccess()
                .executeAndReturnKeyHolder(params);

        Number key = keyHolder.getKey();
        if (key != null) {
            long id = key.longValue();
            e.setId(id);
            log.error("Entry with ID {} created.", id);
        }
    }

    public void addEventWithKey(Event e) {
        SimpleJdbcInsertOperations simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> params = new HashMap<>();
        params.put("event_date", e.getEventDate());
        params.put("name", e.getName());
        params.put("description", e.getDescription());

        Number key = simpleJdbcInsert
                .withTableName("event")
                .usingColumns("event_date", "name", "description")
                .usingGeneratedKeyColumns("id")
                .withoutTableColumnMetaDataAccess()
                .executeAndReturnKey(params);

        long id = key.longValue();
        e.setId(id);

        log.error("Entry with ID {} created.", id);
    }
}
