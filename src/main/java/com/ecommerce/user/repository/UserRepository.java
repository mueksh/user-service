package com.ecommerce.user.repository;

import com.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    // Find by email (used for login / validation)
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find users by role
    List<User> findByRole(String role);

    // Custom search (optional)
    List<User> findByNameContainingIgnoreCase(String name);
}
