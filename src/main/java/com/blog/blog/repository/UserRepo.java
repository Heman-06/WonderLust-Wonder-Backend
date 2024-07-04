package com.blog.blog.repository;

import com.blog.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);


}
