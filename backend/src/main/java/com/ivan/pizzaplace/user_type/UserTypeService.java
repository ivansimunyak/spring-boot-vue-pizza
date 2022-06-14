package com.ivan.pizzaplace.user_type;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserType> getUserTypes() {
        return userTypeRepository.findAll();
    }

    public void insertNewUserType(UserType userType) {
        Optional<UserType> userTypeOptional = userTypeRepository.findUserTypeByName(userType.getName());
        if (userTypeOptional.isPresent()) {
            throw new IllegalStateException("Name exists already!");
        }
        userTypeRepository.save(userType);
    }

    public void updateUserType(UserType userType) {
        Optional<UserType> userTypeOptional = userTypeRepository.findUserTypeById(userType.getId());
        if (userTypeOptional.isPresent()) {
            userTypeRepository.save(userType);
        } else {
            throw new IllegalStateException("User type does not exist or id is not provided!");
        }
    }

    public void removeUserType(UserType userType) {
        Optional<UserType> userTypeOptional = userTypeRepository.findUserTypeById(userType.getId());
        if (userTypeOptional.isPresent()) {
            userTypeRepository.delete(userType);
        } else throw new IllegalStateException("User type does not exist or id is not provided!");
    }
}
