package com.ivan.pizzaplace.user_type;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/usertype")
public class UserTypeController {
    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public List<UserType> getUserType() {
        return userTypeService.getUserTypes();
    }

    @PostMapping(path = "/addusertype")
    public void insertUserType(@RequestBody UserType userType) {
        userTypeService.insertNewUserType(userType);
    }

    @PostMapping(path = "/editusertype")
    public void updateExistingType(@RequestBody UserType userType) {
        userTypeService.updateUserType(userType);
    }

    @PostMapping(path = "/deleteusertype")
    public void deleteUserType(@RequestBody UserType userType) {
        userTypeService.removeUserType(userType);
    }
}
