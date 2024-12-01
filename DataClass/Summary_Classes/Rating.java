
package Summary_Classes;

import java.io.Serializable;

/**
 *
 * @author 24hph
 */
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String raterUsername; // Store username instead of Student object
    private int score;
    
    public Rating(String raterUsername, int score) {
        if (score < 1 || score > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10");
        }
        this.raterUsername = raterUsername;
        this.score = score;
    }
    
    public String getRaterUsername() {
        return raterUsername;
    }
    
    public int getScore() {
        return score;
    }
}