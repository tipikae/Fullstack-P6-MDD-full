package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository.
 * @author tipikae
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Check if a user exists with that email.
     * @param email User email.
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * Check if a user exists with that username.
     * @param username User username.
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * Find a user by its email.
     * @param email User email.
     * @return Optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by its username.
     * @param username User username.
     * @return Optional
     */
    Optional<User> findByUsername(String username);
}
