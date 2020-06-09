package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.Error;
import com.codenation.centraldeerros.repositories.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepository;

    public Iterable<Error> getErrors() {
        return errorRepository.findAll();
    }

    public Optional<Error> getErrorById(Long id) {
        return errorRepository.findById(id);
    }

    public Error saveError(Error error) {
        return errorRepository.save(error);
    }

    public Error updateError(Error error, Long id) {

        Assert.notNull(id, "Update failed");

        Optional<Error> optional = getErrorById(id);

        if (optional.isPresent()) {
            Error db = optional.get();
            db.setTitle((error.getTitle()));
            db.setDetails((error.getDetails()));
            db.setEvents((error.getEvents()));
//          db.setCollectedBy((user.getName()));

            System.out.println("Error id: " + db.getId());

            errorRepository.save(db);

            return db;
        } else {
            throw new RuntimeException("Update failed");
        }
    }

    public boolean deleteError(Long id) {
        if (getErrorById(id).isPresent()) {
            errorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
