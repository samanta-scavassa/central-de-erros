package com.codenation.centraldeerros.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "environment")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", length = 50, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    private String name;

    @OneToMany(mappedBy = "environment")
    @JsonIgnore
    private List<Log> logs;
}
