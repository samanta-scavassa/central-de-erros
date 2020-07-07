package com.codenation.centraldeerros.services;

import com.codenation.centraldeerros.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if (user.getEmail().equals(email)) {
            return new User(user.getEmail(), user.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found with e-mail: " + email);
        }
    }

}
