package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Error;
import org.springframework.data.repository.CrudRepository;


public interface ErrorRepository extends CrudRepository<Error, Long> {

    public Error findErrorByTitle(String title);
}
