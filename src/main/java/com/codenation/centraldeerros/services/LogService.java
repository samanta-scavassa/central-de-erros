package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Log getLogByLevel(String level) {
        return logRepository.findLogByLevel(level);
    }

//    public Log getLogByDescription(String description) {
//        return logRepository.findLogByDescription(description);

    //ambiente
//    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }
}
