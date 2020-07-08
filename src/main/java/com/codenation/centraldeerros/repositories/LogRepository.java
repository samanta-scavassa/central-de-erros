package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
    Iterable<Log> findLogByLevel(String level);

    Iterable<Log> findLogByDescription(String description);

    Iterable<Log> findLogByUser(User user_id);

    Iterable<Log> findLogByEnvironment(Environment environment_id);

    Iterable<Log> findAllByOrderByLevelAsc();
}
