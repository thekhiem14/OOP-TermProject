
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public Timestamp getUploadDate() {return uploadDate;}
    
    public List<Comment> getComments()
    {
        return this.comments;
    }
    
    public void addComments(Comment newComment)
    {
        this.comments.add(newComment);
    }
    
    public void deleteComments(Comment selectedComment)
    {
        this.comments.remove(selectedComment);
    }
    
    public void addRating(Rating newRating)
    {
        this.ratings.add(newRating);
    }
    
    public void deleteRating(Rating selectedRating)
    {
        this.ratings.remove(selectedRating);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  
        }
        Material material = (Material) obj;  
        return downloadUrl.equals(material.downloadUrl);  
    }

    @Override
    public int hashCode() {
        int result = downloadUrl.hashCode();  
        result = 31 * result; 
        return result;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }
}
