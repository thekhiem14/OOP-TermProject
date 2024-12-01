
package Summary_Classes;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author 24hph
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String author; // Store username instead of User object
    private String content;
    private Timestamp timestamp;
    
    public Comment(String authorUsername, String content) {
        this.author = authorUsername;
        this.content = content;
         this.timestamp = new Timestamp(System.currentTimeMillis());
    }
    
    public String getAuthorUsername() {
        return author;
    }
    
    public String getContent() {
        return content;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
}

