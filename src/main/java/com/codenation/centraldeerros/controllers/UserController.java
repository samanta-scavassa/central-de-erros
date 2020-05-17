package com.codenation.centraldeerros.controllers;

import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {

        Optional<User> user = service.getUserById(id);

        return user
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody User user) {

        try {
            User u = service.saveUser(user);
            URI location = getUri(u.getId());
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
    public ResponseEntity putUser(@PathVariable ("id") Long id, @RequestBody User user) {

        user.setId(id);

        User u = service.updateUser(user, id);

        return u != null ?
                ResponseEntity.ok(u) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        boolean ok = service.deleteUser(id);

        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
