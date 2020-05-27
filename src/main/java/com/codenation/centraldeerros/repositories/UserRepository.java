package com.codenation.centraldeerros.repositories;

import com.codenation.centraldeerros.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
