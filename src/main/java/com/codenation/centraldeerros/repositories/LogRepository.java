package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository <Log, Long> {
    public Iterable<Log> findLogByLevel(String level);
    public Iterable<Log> findLogByDescription(String description);
    public Iterable<Log> findLogByUser(User user_id);
    public Iterable<Log> findLogByEnvironment(Environment environment_id);
}
