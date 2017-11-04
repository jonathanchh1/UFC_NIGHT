package com.example.jonat.services.Models;

/**
 * Created by jonat on 11/4/2017.
 */

public class Comment {

    public String uid;
    public String author;
    public String text;
    public String UserImage;

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String uid, String author, String text, String userImage) {
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.UserImage = userImage;
    }

}
