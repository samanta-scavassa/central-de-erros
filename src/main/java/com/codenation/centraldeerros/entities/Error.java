package com.codenation.centraldeerros.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "error")
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", length = 100, nullable = false)
    @NotNull(message = "O título é obrigatório")
    private String title;

    @Column(name = "details", length = 500, nullable = false)
    @NotNull(message = "O detalhe do erro é obrigatório")
    private String details;

    @Column(name = "events", length = 100, nullable = false)
    private String events;

    @Column(name = "collectedBy", length = 50, nullable = false)
    private String collectedBy;

}
