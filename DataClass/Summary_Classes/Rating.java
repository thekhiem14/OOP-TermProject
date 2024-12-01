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
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String raterUsername; // Store username instead of Student object
    private int score;
    
    public Rating(String raterUsername, int score) {
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
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
