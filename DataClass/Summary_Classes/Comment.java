
package Summary_Classes;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private User author; // Store username instead of User object
    private String content;
    private Timestamp timestamp;
    
    public Comment(User authorUsername, String content) {
        this.author = authorUsername;
        this.content = content;
         this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    
    public String getAuthorUsername() {
        return author.getEmail();
    }
    
    public String getContent() {
        return content;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  
        }
        Comment comment = (Comment) obj;  
        return author.equals(comment.author) && content.equals(comment.content);  
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();  
        result = 31 * result + content.hashCode(); 
        return result;
    }
}
