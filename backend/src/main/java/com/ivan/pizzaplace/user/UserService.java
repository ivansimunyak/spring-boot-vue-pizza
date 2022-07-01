package com.ivan.pizzaplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
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

    public void deleteProfile(User user) {
        User foundUser = userRepository.findUserById(user.getId());
        System.out.println(user);
        if (foundUser != null) {
            if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                userRepository.deleteById(user.getId());
            } else throw new IllegalStateException("Password not valid!");
        } else throw new IllegalStateException("User does not exist or id is not provided!");
    }

    public void changePassword(User user) {
        User foundUser = userRepository.findUserById(user.getId());
        if (foundUser != null) {
            foundUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(foundUser);
        } else throw new IllegalStateException("User does not exist or id is not provided!");
    }

    public void updateProfile(User user) {
        User foundUser = userRepository.findUserById(user.getId());
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (!Objects.equals(existingUser.get().getId(), user.getId())) {
                throw new IllegalStateException("Email is taken!");
            }
        }
        if (foundUser != null) {
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setEmail(user.getEmail());
            foundUser.setAdress(user.getAdress());
            foundUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(foundUser);
        } else throw new IllegalStateException("User does not exist or id is not provided!");
    }


}
