package com.ivan.pizzaplace.user_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    @Query(value = "SELECT ut FROM UserType ut WHERE ut.name=?1")
    Optional<UserType> findUserTypeByName(String name);

    @Query(value = "SELECT ut FROM UserType ut WHERE ut.id=?1")
    Optional<UserType> findUserTypeById(Long id);
}
