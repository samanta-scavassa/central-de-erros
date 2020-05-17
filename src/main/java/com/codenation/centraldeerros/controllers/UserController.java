package com.codenation.centraldeerros.controllers;

import com.codenation.centraldeerros.domain.User;
import com.codenation.centraldeerros.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public Iterable<User> get() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable("id") Long id) {
        return service.getUserById(id);
    }

    @PostMapping
    public String user(@RequestBody User user) {
        User u = service.save(user);

        return "User saved with success: " + u.getEmail();
    }

    @PutMapping("/{id}")
    public String put(@PathVariable ("id") Long id, @RequestBody User user) {
        User u = service.update(user, id);

        return "User updated with success: " + u.getEmail();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "User deleted with success";
    }
}
