package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LogRepository extends CrudRepository<Log, Long> {

    @Query(value = "select * from log_error_center where user_id = :userId and environment_id = :environmentId and level = :level", nativeQuery = true)
    Iterable<Log> findLogByLevel(@Param("userId") Long userId, @Param("environmentId") Long environmentId, String level);

    @Query(value = "SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId and  lower(l.description) like %:description%", nativeQuery = true)
    Iterable<Log> findLogByDescription(@Param("userId") Long userId, @Param("environmentId") Long environmentId, @Param("description") String description);

    @Query(value = "SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId ", nativeQuery = true)
    Iterable<Log> findLogByEnvironment(@Param("userId") Long userId, @Param("environmentId") Long environmentId);

    @Query(value = "SELECT * FROM log_error_center as l where l.user_id= :userId and l.environment_id = :environmentId order by level ", nativeQuery = true)
    Iterable<Log> findLogByLevelOrder(@Param("userId") Long userId, @Param("environmentId") Long environmentId);

    @Query(value = "select count(lc.event) as id, lc.event, lc.description, lc.level, lc.user_id, lc.environment_id" +
            "  from log_error_center lc" +
            " where lc.user_id = :userId" +
            "   and environment_id = :environmentId" +
            " group by lc.event, lc.description, lc.level, lc.user_id, lc.environment_id" +
            " order by id desc", nativeQuery = true)
    Iterable<Log> findAllByOrderByFrequency(@Param("userId") Long userId, @Param("environmentId") Long environmentId);
}
