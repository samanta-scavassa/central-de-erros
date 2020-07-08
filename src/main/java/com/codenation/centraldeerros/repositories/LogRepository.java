package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Environment;
import com.codenation.centraldeerros.entities.Log;
import com.codenation.centraldeerros.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LogRepository extends CrudRepository<Log, Long> {
    Iterable<Log> findLogByLevel(String level);

    @Query( value="SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId and  lower(l.description) like %:description%", nativeQuery = true )
    Iterable<Log> findLogByDescription(@Param("userId") Long userId, @Param("environmentId") Long environmentId, @Param("description") String description);

    @Query( value="SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId ", nativeQuery = true )
    Iterable<Log> findLogByEnvironment(@Param("userId") Long userId, @Param("environmentId") Long environmentId);

    @Query( value="SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId order by level ", nativeQuery = true )
    Iterable<Log> findLogByLevelOrder(@Param("userId") Long userId, @Param("environmentId") Long environmentId);
}
