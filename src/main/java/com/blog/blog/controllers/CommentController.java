package com.blog.blog.controllers;

import com.blog.blog.entity.Comment;
import com.blog.blog.entity.Post;
import com.blog.blog.repository.CommentRepository;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create/{email}/{postId}")
    public ResponseEntity<Comment> createComment(@PathVariable String email, @PathVariable String postId, @RequestBody String content) {
        Comment createdComment = commentService.createComment(email, postId, content);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }



    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment comment) {
        Optional<Comment> updatedComment = commentService.updateComment(id, comment);
        return updatedComment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/user/{email}")
//    public List<Comment> getCommentsByUserEmail(@PathVariable String email) {
//        return commentService.getCommentsByUserEmail(email);
//    }

//    @GetMapping("/user/{email}/details")
//    public Map<String, Object> getCommentsAndUserByEmail(@PathVariable String email) {
//        return commentService.getCommentsAndUserByEmail(email);
//    }
//
//    @GetMapping("/posts/{postId}/comments")
//    public List<Comment> getCommentsByPost(@PathVariable String postId) {
//        return commentService.getCommentsByPost(postId);
//    }
}
