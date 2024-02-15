package com.fortytwotalents.spring.clients.demo.data.proxy.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@Table(name = "EVENT")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class EventEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private LocalDateTime eventDate;

    @NonNull
    private String name;

    @NonNull
    private String description;

}
