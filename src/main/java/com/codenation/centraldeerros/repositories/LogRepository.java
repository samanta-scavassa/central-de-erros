package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository <Log, Long> {

    public Log findLogByLevel(String level);
    //public Log findLogByDescription(String log);
    //fazer o de ambiente (igual ao do user)
}
