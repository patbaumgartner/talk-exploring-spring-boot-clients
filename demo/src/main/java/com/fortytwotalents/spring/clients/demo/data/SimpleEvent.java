package com.fortytwotalents.spring.clients.demo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SimpleEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @NonNull
    private LocalDateTime eventDate;

    @NonNull
    private String name;

    @NonNull
    private String description;

}
