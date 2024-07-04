package com.blog.blog.repository;

import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByUser(User user);

}
