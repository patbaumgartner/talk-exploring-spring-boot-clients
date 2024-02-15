package com.fortytwotalents.spring.clients.demo.data;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private Long id;

    @NonNull
    private LocalDateTime eventDate;

    @NonNull
    private String name;

    @NonNull
    private String description;

}
