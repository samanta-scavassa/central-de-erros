package com.codenation.centraldeerros.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "log_error_center")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="level", length = 20, nullable = false)
    @NotNull(message = "O level é obrigatório")
    private String level;

    @Column(name="description", length = 100, nullable = false)
    @NotNull(message = "A descrição é obrigatória")
    private String description;

    @Column(name="event", nullable = false)
    @NotNull(message = "O event é obrigatório")
    private Long event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;
}