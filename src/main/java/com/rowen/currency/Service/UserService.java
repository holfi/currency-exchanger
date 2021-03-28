package com.rowen.currency.Service;


import com.rowen.currency.Exception.UserAlreadyExistException;
import com.rowen.currency.Model.Roles;
import com.rowen.currency.Model.User;
import com.rowen.currency.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Transactional
    public User registerNewUser(User user) throws UserAlreadyExistException {
        if (usernameExist(user.getUsername()))
            throw new UserAlreadyExistException("There is an account with that username: " + user.getUsername());

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setActive(true);
        user.setRole(Collections.singleton(Roles.USER));

        return user;
    }

    public boolean usernameExist(String username) {
        return userRepo.findByUsername(username) != null;
    }

}
