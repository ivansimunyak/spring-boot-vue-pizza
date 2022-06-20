package com.ivan.pizzaplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        User userOptional = userRepository.findUserByUsername(username);
        if (userOptional != null) {
            return userOptional;
        } else {
            throw new IllegalStateException("User not found!");
        }

    }

    public void insertNewUser(User user) {
        User userOptional = userRepository.findUserByUsername(user.getUsername());
        if (userOptional != null) {
            throw new IllegalStateException("Username exists already!");
        }
        userRepository.save(user);
    }

    public void updateUsersType(User user) {
        User foundUser = userRepository.findUserById(user.getId());
        if (foundUser != null) {
            foundUser.setUserType(user.getUserType());
            userRepository.save(foundUser);
        } else throw new IllegalStateException("User does not exist or id is not provided!");
    }

    public void deleteUser(User user) {
        User foundUser = userRepository.findUserById(user.getId());
        if (foundUser != null) {
            userRepository.delete(foundUser);
        } else throw new IllegalStateException("User does not exist or id is not provided!");
    }


}
