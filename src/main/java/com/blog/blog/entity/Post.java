//package com.blog.blog.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.util.List;
//import java.util.Optional;
//
//@Document(collection = "posts")
//public class Post {
//    @Id
//    private String id;
//    private String title;
//    private String type;
//    private String content;
//
//
//    // Constructors
//    public Post() {
//    }
//
//    public Post(String title, String type, String content) {
//        this.title = title;
//        this.type = type;
//        this.content = content;
//    }
//
//    // Getters and setters
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Post{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", type='" + type + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//}


package com.blog.blog.entity;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String type;
    private String content;

    private byte[] image;

    @DBRef
    private User user;

    @DBRef
    private List<Comment> comments;

    @CreatedDate
    private LocalDateTime createdDate;


    // Constructors
    public Post() {
    }

    public Post(String title, String type, String content, User user) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
