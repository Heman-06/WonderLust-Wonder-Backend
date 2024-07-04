package com.blog.blog.controllers;

import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepo;
import com.blog.blog.services.PostService;
import com.blog.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for this controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private  PostRepository postRepository;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @GetMapping("/getAllPost")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable String id) {
//        Optional<Post> post = postService.getPostById(id);
//        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/user/{email}")
    public Map<String, Object> getPostsByUserEmail(@PathVariable String email) {
        return postService.getPostsByUserEmail(email);
    }

    @GetMapping("/user/post/{id}")
    public Map<String, Object> getUserAndPostsByUserId(@PathVariable String id) {
        return postService.getUserAndPostsByUserId(id);
    }

//@GetMapping("/user/{email}")
//public Map<String, Object> getUserPostsByEmail(@PathVariable String email) {
//    return postService.getUserPostsByEmail(email);
//}

    @PostMapping("/create/{email}")
    public ResponseEntity<Post> createPost(@PathVariable String email,
                                           @RequestParam("title") String title,
                                           @RequestParam("type") String type,
                                           @RequestParam("content") String content,
                                           @RequestParam("image") MultipartFile image) {
        // Handle the creation logic here
        // Example:
        Post post = postService.createPost(email, title, type, content, image);
        return ResponseEntity.ok(post);
    }

//    @PostMapping("/create")
//    public ResponseEntity<Post> createPost(@RequestBody Post post) {
//        Post createdPost = postService.createPost(post);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//    }

//    @PostMapping("/create/{email}")
//    public Post createPost(@PathVariable String email, @RequestBody Post post) {
//        return postService.createPost(email, post);
//    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> updatedPost = postService.updatePost(id, post);
        return updatedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getSpecific(@PathVariable String id) {
        // Use findById to retrieve the post by ID
        // findById returns an Optional<Post>, so handle presence or absence of the post
        return postRepository.findById(id)
                .map(ResponseEntity::ok) // Map the found post to ResponseEntity.ok
                .orElse(ResponseEntity.notFound().build()); // Return 404 if post is not found
    }





}
