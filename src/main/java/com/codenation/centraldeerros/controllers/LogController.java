package com.codenation.centraldeerros.controllers;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogService service;

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

    @GetMapping("/level/{level}")
    public Iterable<Log> getLogByLevel(@PathVariable("level") String level) {
        return service.getLogByLevel(level);
    }

    @GetMapping("/levelOrder")
    public Iterable<Log> getLogByLevelOrder(){
        return service.getLogByLevelOrder();
    }

    @GetMapping("/description/{description}")
    public Iterable<Log> getLogByDescription(@PathVariable("description") String description) {
        return service.getLogByDescription(description);
    }

    @GetMapping("/user/{user_id}")
    public Iterable<Log> getLogByUser(@PathVariable("user_id") User user_id) {
        return service.getLogByUser(user_id);
    }

    @GetMapping("/env/{environment_id}")
    public Iterable<Log> getLogByUser(@PathVariable("environment_id") Environment environment_id) {
        return service.getLogByEnvironment(environment_id);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PostMapping
    public ResponseEntity postLog(@Valid  @RequestBody Log log) {
        try {
            Log l = service.saveLog(log);
            URI location = getUri(l.getId());
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
