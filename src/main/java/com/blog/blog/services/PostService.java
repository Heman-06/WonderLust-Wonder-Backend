package com.blog.blog.services;

import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepo userRepo;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

//    public Post createPost(Post post) {
//        return postRepository.save(post);
//    }
//public Post createPost(String email, Post post) {
//    Optional<User> userOptional = userRepo.findByEmail(email);
//    if (userOptional.isPresent()) {
//        User user = userOptional.get();
//        post.setUser(user); // Set the user reference
//        return postRepository.save(post);
//    } else {
//        throw new RuntimeException("User not found with email: " + email);
//    }
//}


    public Post createPost(String email, String title, String type, String content, MultipartFile image) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = new Post(title, type, content, user);
            // Handle the image file if needed
            if (image != null && !image.isEmpty()) {
                try {
                    byte[] imageBytes = image.getBytes();
                    post.setImage(imageBytes); // Set image bytes to the post
                } catch (IOException e) {
                    throw new RuntimeException("Failed to store image", e);
                }
            }
            return postRepository.save(post);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }



    public Optional<Post> updatePost(String id, Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            post.setId(id);
            return Optional.of(postRepository.save(post));
        }
        return Optional.empty();
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }


    //    public List<Post> getPostsByUserEmail(String email) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return postRepository.findByUser(user);
//        } else {
//            throw new RuntimeException("User not found with email: " + email);
//        }
//    }
    public Map<String, Object> getPostsByUserEmail(String email) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Post> posts = postRepository.findByUser(user);

            // Construct custom response
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("posts", posts);

            return response;
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

        public Map<String, Object> getUserAndPostsByUserId(String id) {
            Optional<User> userOptional = userRepo.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                List<Post> posts = postRepository.findByUser(user);

                // Construct custom response
                Map<String, Object> response = new HashMap<>();
                response.put("user", user);
                response.put("posts", posts);

                return response;
            } else {
                throw new RuntimeException("User not found with id: " + id);
            }
        }

//    public Map<String, Object> getUserPostsByEmail(String email) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<Post> posts = postRepository.findByUser(user);
//
//            // Construct custom response
//            Map<String, Object> response = new HashMap<>();
//            response.put("user", user);
//            response.put("posts", posts);
//
//            return response;
//        } else {
//            throw new RuntimeException("User not found with email: " + email);
//        }
//    }
    }

