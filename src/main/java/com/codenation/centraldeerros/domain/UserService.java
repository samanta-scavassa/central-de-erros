package com.codenation.centraldeerros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private Repository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user, Long id) {

        Assert.notNull(id, "Update failed");

        Optional<User> optional = getUserById(id);

        if(optional.isPresent()) {
            User db = optional.get();
            db.setEmail((user.getEmail()));
            db.setPassword(user.getPassword());

            System.out.println("User id: " + db.getId());

            userRepository.save(db);

            return db;
        } else {
            throw new RuntimeException("Update failed");
        }
    }

    public void delete(Long id) {
        Optional<User>user = getUserById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        }

    }

}
