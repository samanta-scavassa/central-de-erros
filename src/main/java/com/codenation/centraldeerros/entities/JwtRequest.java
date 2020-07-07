package com.codenation.centraldeerros.entities;

import java.io.Serializable;

public class JwtRequest implements Serializable {


    private static final long serialVersionUID = 2949435881077855291L;
    private String email;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
