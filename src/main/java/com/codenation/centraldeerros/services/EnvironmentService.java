package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.repositories.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    public Iterable<Environment> getEnvironments() {
        return environmentRepository.findAll();
    }

    public Optional<Environment> getEnvironmentById(Long id) {
        return environmentRepository.findById(id);
    }

    public Environment saveEnvironment(Environment environment) {
        return environmentRepository.save(environment);
    }

    public Environment updateEnvironment(Environment environment, Long id) {

        Assert.notNull(id, "Update failed");

        Optional<Environment> optional = getEnvironmentById(id);

        if(optional.isPresent()) {
            Environment db = optional.get();
            db.setName((environment.getName()));

            System.out.println("Environment id: " + db.getId());

            environmentRepository.save(db);

            return db;
        } else {
            return null;
        }
    }

    public boolean deleteEnvironment(Long id) {
        if(getEnvironmentById(id).isPresent()) {
            environmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
