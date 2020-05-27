package com.codenation.centraldeerros.controllers;


import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.services.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/env")
public class EnvironmentController {

    @Autowired
    private EnvironmentService service;

    @GetMapping()
    public ResponseEntity<Iterable<Environment>> getEnvironments() {
        return ResponseEntity.ok(service.getEnvironments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Environment> getEnvironmentById(@PathVariable("id") Long id) {

        Optional<Environment> environment = service.getEnvironmentById(id);

        return environment
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postEnvironment(@RequestBody Environment environment) {

        try {
            Environment env = service.saveEnvironment(environment);
            URI location = getUri(env.getId());
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity putEnvironment(@PathVariable ("id") Long id, @RequestBody Environment environment) {

        environment.setId(id);

        Environment env = service.updateEnvironment(environment, id);

        return env != null ?
                ResponseEntity.ok(env) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEnvironment(@PathVariable("id") Long id) {
        boolean ok = service.deleteEnvironment(id);

        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
