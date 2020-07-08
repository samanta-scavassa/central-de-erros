package com.codenation.centraldeerros.utilities;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Uri {
    public static URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
