package com.blog.blog.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comments")
@Data
public class Comment {

    @Id
    public String id;


    public String comment;

    @DBRef
    private User user; // User who commented on the post

    private String postId; // This should match your method parameter





}
