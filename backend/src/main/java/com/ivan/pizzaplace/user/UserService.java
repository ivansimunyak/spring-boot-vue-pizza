package com.ivan.pizzaplace.user;

import com.ivan.pizzaplace.user_type.UserType;
import com.ivan.pizzaplace.user_type.UserTypeRepository;
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
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
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

    public void register(User user) {
        User foundUser = userRepository.findUserByUsername(user.getUsername());
        if (foundUser != null) {
            throw new IllegalStateException("Username already exists!");
        }
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("Email already exists!");
        }
        Optional<UserType> optionalUserType = userTypeRepository.findUserTypeByName("Customer");
        if (optionalUserType.isPresent()) {
            UserType userType = new UserType();
            userType.setName(optionalUserType.get().getName());
            userType.setId(optionalUserType.get().getId());
            user.setUserType(userType);
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            System.out.println("User:" + user);
            userRepository.save(user);
        }
    }


}
