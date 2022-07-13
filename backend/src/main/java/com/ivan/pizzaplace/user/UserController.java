package com.ivan.pizzaplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/getuser/")
    public User getUser(@RequestParam String username) {
        return userService.getUser(username);
    }

    @PostMapping(path = "/updateusertype")
    public void updateUsersType(@RequestBody User user) {
        userService.updateUsersType(user);
    }

    @PostMapping(path = "/register")
    public void registerUser(@RequestBody User user) {
        userService.insertNewUser(user);
    }

    @PostMapping(path = "/deleteuser")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @PostMapping(path = "/deleteprofile")
    public void deleteProfile(@RequestBody User user) {
        userService.deleteProfile(user);
    }

    @PostMapping(path = "/changepassword")
    public void changePassword(@RequestBody User user) {
        userService.changePassword(user);
    }

    @PostMapping(path = "/updateprofile")
    public void updateProfile(@RequestBody User user) {
        userService.updateProfile(user);
    }

    @PostMapping(path = "/registeruser")
    public void register(@RequestBody User user) {
        userService.register(user);
    }
}
