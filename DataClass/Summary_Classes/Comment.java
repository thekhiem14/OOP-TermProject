/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.Serializable;

/**
 *
 * @author 24hph
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private User author; // Store username instead of User object
    private String content;
    private String timestamp;
    
    public Comment(User authorUsername, String content, String timestamp) {
        this.author = authorUsername;
        this.content = content;
        this.timestamp = timestamp;
    }
    
    public User getAuthorUsername() {
        return author;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
}

