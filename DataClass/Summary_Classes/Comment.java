package Summary_Classes;
import java.sql.*;
public class Comment {
    private String description, image, author, materialId, status, parentId;
    private Timestamp createAt, deleteAt;
    public Comment(String description, String image, String author, String materialId, String parentId) {
        this.description = description;
        this.image = image;
        this.author = author;
        this.materialId = materialId;
        this.parentId = parentId;
        this.status = "active";
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    public void deleteComment(){
        this.status = "inactive";
        this.deleteAt = new Timestamp(System.currentTimeMillis());
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }
    
}
