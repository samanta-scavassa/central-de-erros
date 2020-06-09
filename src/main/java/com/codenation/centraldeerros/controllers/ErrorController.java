package com.codenation.centraldeerros.controllers;

import com.codenation.centraldeerros.entities.Error;
import com.codenation.centraldeerros.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/errors")
public class ErrorController {

    @Autowired
    private ErrorService service;

    @GetMapping()
    public ResponseEntity<Iterable<Error>> getErrors() {
        return ResponseEntity.ok(service.getErrors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Error> getErrorById(@PathVariable("id") Long id) {

        Optional<Error> error = service.getErrorById(id);

        return error.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postError(@RequestBody Error error) {

        try {
            Error u = service.saveError(error);
            URI location = getUri(u.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity putError(@PathVariable("id") Long id, @RequestBody Error error) {
        error.setId(id);
        Error u = service.updateError(error, id);
        return u != null ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteError(@PathVariable("id") Long id) {
        boolean ok = service.deleteError(id);

        return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
