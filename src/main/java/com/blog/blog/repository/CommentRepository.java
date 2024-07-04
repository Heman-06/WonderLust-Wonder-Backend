package com.blog.blog.repository;

import com.blog.blog.entity.Comment;
import com.blog.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByUserAndPostId(User user, String postId);
    List<Comment> findByPostId(String postId);

}
