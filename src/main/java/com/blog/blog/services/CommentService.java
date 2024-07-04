package com.blog.blog.services;

import com.blog.blog.entity.Comment;
import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(String userEmail, String postId, String content) {
        Optional<User> userOptional = userRepo.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Comment comment = new Comment();
            comment.setComment(content.trim()); // Remove any unwanted whitespace
            comment.setUser(user);
            comment.setPostId(postId);
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("User not found with email: " + userEmail);
        }
    }


    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }


    public Optional<Comment> updateComment(String id, Comment updatedComment) {
        return commentRepository.findById(id)
                .map(existingComment -> {
                    existingComment.setComment(updatedComment.getComment());
                    return commentRepository.save(existingComment);
                });
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

//    public List<Comment> getCommentsByUserEmail(String email) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return commentRepository.findByUser(user);
//        } else {
//            throw new RuntimeException("User not found with email: " + email);
//        }
//    }

//    public Map<String, Object> getCommentsAndUserByEmail(String email) {
//        Optional<User> userOptional = userRepo.findByEmail(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<Comment> comments = commentRepository.findByUser(user);
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("user", user);
//            response.put("comments", comments);
//
//            return response;
//        } else {
//            throw new RuntimeException("User not found with email: " + email);
//        }
//    }

//    public List<Comment> getCommentsByPost(String postId) {
//        return commentRepository.findByPostId(postId);
//    }
}
