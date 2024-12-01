/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author 24hph
 */
public class Material implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String description;
    private String downloadUrl;
    private Timestamp uploadDate;
    private List<Comment> comments;
    private List<Rating> ratings;
    private User author;
    
    public Material(String title, String description, String downloadUrl, User author) {
        this.title = title;
        this.description = description;
        this.downloadUrl = downloadUrl;
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.uploadDate = new Timestamp(System.currentTimeMillis());
        this.author = author;
    }
    
    // Existing methods for comments, ratings, etc. remain the same
      public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public Timestamp getUploadDate() {return uploadDate;}
}
