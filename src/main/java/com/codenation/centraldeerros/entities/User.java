package com.codenation.centraldeerros.entities;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_register")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", length = 50, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    private String name;

    @Column(name="email", length = 50, nullable = false, unique = true)
    @Email(message = "Insira um e-mail válido")
    @NotNull(message = "O e-mail é obrigatório")
    private String email;

    @Column(name="password", length = 8, nullable = false)
    @NotNull(message = "A senha é obrigatória")
    @Length(max = 8, min = 6, message= "A senha deve conter entre 6 e 8 caracteres")
    private String password;


}
