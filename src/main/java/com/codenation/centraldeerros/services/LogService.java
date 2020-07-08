package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public Iterable<Log> getLogs() {
        return logRepository.findAll();
    }

    public Optional<Log> getLogById(Long id) {
        return logRepository.findById(id);
    }

    public Iterable<Log> getLogByLevel(String level) {
        return logRepository.findLogByLevel(level);
    }

    public Iterable<Log> getLogByDescription(String description) {
        return logRepository.findLogByDescription(description);
    }

    public Iterable<Log> getLogByUser(User user_id) {
        return logRepository.findLogByUser(user_id);
    }

    public Iterable<Log> getLogByEnvironment(Environment environment_id) {
        return logRepository.findLogByEnvironment(environment_id);
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    public Iterable<Log> getLogByLevelOrder() {
        return logRepository.findAllByOrderByLevelAsc();
    }
}
