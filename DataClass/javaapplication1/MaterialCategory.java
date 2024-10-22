package javaapplication1;
import java.sql.*;
public class MaterialCategory {
    private String id, title, parentId, description, status, slug;
    private int position;
    private Timestamp createAt, deleteAt;

    public MaterialCategory(String id, String title, String parentId, String description, Integer position, String slug) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.description = description;
        this.position = position;
        this.slug = slug;
        this.status= "active";
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    public void deleteCategory(){
        this.status = "inactive";
        this.deleteAt = new Timestamp(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getParentId() {
        return parentId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getSlug() {
        return slug;
    }

    public int getPosition() {
        return position;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
