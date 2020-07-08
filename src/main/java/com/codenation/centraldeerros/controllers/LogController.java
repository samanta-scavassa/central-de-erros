package com.codenation.centraldeerros.controllers;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.services.EnvironmentService;
import com.codenation.centraldeerros.services.LogService;
import com.codenation.centraldeerros.services.UserService;
import com.codenation.centraldeerros.utilities.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService service;

    @Autowired
    UserService userService;

    @Autowired
    EnvironmentService environmentService;

    @GetMapping()
    public ResponseEntity<Iterable<Log>> getLogs() {
        return ResponseEntity.ok(service.getLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable("id") Long id) {
        Optional<Log> log = service.getLogById(id);
        return log
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{user_id}/{environment_id}/level/{level}")
    public ResponseEntity<Iterable<Log>> getLogByLevel(@PathVariable("user_id") Long userId, @PathVariable("environment_id") Long environmentId, @PathVariable("level") String level) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Environment> env = environmentService.getEnvironmentById(environmentId);
        if (!env.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Iterable<Log> log = service.getLogByLevel(userId, environmentId, level);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/{user_id}/{environment_id}/frequencyOrder")
    public ResponseEntity<Iterable<Log>> getLogByFrequencyOrder(@PathVariable("user_id") Long userId, @PathVariable("environment_id") Long environmentId) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Environment> env = environmentService.getEnvironmentById(environmentId);
        if (!env.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Iterable<Log> log = service.getLogByFrequencyOrder(userId, environmentId);
        return ResponseEntity.ok(log);
    }

    @GetMapping("user/{user_id}/env/{environment_id}/levelOrder")
    public ResponseEntity<Iterable<Log>> getLogByLevelOrder(@PathVariable("user_id") Long userId, @PathVariable("environment_id") Long environmentId) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Environment> env = environmentService.getEnvironmentById(environmentId);
        if (!env.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Iterable<Log> log = service.getLogByLevelOrder(userId, environmentId);
        return ResponseEntity.ok(log);
    }

    @GetMapping("user/{user_id}/env/{environment_id}/description/{description}")
    public ResponseEntity<Iterable<Log>> getLogByDescription(@PathVariable("user_id") Long userId, @PathVariable("environment_id") Long environmentId, @PathVariable("description") String description) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Environment> env = environmentService.getEnvironmentById(environmentId);
        if (!env.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Iterable<Log> log = service.getLogByDescription(userId, environmentId, description);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/user/{user_id}/env/{environment_id}")
    public ResponseEntity<Iterable<Log>> getLogByEnvironment(@PathVariable("user_id") Long userId, @PathVariable("environment_id") Long environmentId) {

        Optional<User> user = userService.getUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Environment> env = environmentService.getEnvironmentById(environmentId);
        if (!env.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Iterable<Log> log = service.getLogByEnvironment(userId, environmentId);
        return ResponseEntity.ok(log);
    }

    @PostMapping
    public ResponseEntity postLog(@Valid @RequestBody Log log) {
        try {
            Log l = service.saveLog(log);
            URI location = Uri.getUri(l.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}