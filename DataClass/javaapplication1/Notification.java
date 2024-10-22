package javaapplication1;

import java.sql.*;

public class Notification {
    private String id, userId, content, materialId, commentId, ratingId;
    private boolean read;

    public void NotificationMaterial(String id, String userId, String materialId, String content) {
        this.id = id;
        this.read = false;
        this.content=content;
        this.userId = userId;
        this.materialId = materialId;
    }

    public void NotificationComment(String id, String userId, String commentId, String content) {
        this.id = id;
        this.read = false;
        this.content=content;
        this.userId = userId;
        this.commentId = commentId;
    }

    public void NotificationRating(String id, String userId, String ratingId, String content) {
        this.id = id;
        this.read = false;
        this.content=content;
        this.userId = userId;
        this.ratingId = ratingId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getRatingId() {
        return ratingId;
    }

    public boolean getRead() {
        return read;
    }
    public void setRead() {
        this.read = true;
    }
}
