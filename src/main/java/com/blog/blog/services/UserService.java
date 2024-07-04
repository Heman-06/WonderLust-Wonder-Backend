package com.blog.blog.services;

import com.blog.blog.entity.User;
import com.blog.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(String id) {
        try {
            return Optional.ofNullable(userRepository.findById(id).orElse(null));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
//    public Optional<User> updateUser(String email, User user) {
//        return userRepository.findByEmail(email)
//                .map(existingUser -> {
//                    existingUser.setName(user.getName());
//                    existingUser.setAddress(user.getAddress());
//                    existingUser.setPhoneNumber(user.getPhoneNumber());
//                    existingUser.setEmail(user.getEmail());
//                    existingUser.setWebsite(user.getWebsite());
//                    existingUser.setImage(user.getImage());
//                    return userRepository.save(existingUser);
//                });
//    }
    public Optional<User> updateUser(String email, User user) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setAddress(user.getAddress());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    // Do not update the email field
                    // existingUser.setEmail(user.getEmail());
                    existingUser.setWebsite(user.getWebsite());
                    existingUser.setImage(user.getImage());
                    return userRepository.save(existingUser);
                });
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }


    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
