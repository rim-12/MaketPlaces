package com.example.UsersServices.Repository;

import com.example.UsersServices.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByLastName(String lastName);
    Optional<User> findByEmail(String email);

}
