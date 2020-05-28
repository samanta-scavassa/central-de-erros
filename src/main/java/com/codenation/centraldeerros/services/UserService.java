package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.User;
import com.codenation.centraldeerros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {

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

    public boolean deleteUser(Long id) {
        if(getUserById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
