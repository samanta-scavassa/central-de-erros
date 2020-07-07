package com.codenation.centraldeerros.entities;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -4306547605685990819L;

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

}
