package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Iterable<Log> getLogByUser(User userId) {
        return logRepository.findLogByUser(userId);
    }

    public Iterable<Log> getLogByEnvironment(Environment environmentId) {
        return logRepository.findLogByEnvironment(environmentId);
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    public Iterable<Log> getLogByLevelOrder() {
        return logRepository.findAllByOrderByLevelAsc();
    }
}
