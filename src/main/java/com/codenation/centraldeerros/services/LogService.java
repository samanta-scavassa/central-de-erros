package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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

    public Iterable<Log> getLogByDescription(Long userId, Long environmentId, String description) {
        description = description.toLowerCase();
        return logRepository.findLogByDescription(userId, environmentId, description);
    }

    public Iterable<Log> getLogByEnvironment(Long userId, Long environmentId) {
        return logRepository.findLogByEnvironment(userId, environmentId);
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    public Iterable<Log> getLogByLevelOrder(Long userId, Long environmentId) {
        return logRepository.findLogByLevelOrder(userId, environmentId);
    }
}
