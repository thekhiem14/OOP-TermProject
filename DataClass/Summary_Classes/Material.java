package Summary_Classes;
import java.sql.*;
public class Material {
    private String id, title, description, materialCategoryId, thumbnail, status, slug, creatBy,filepath;
    private float point;
    private Timestamp createdAt, deletedAt;

    public Material(String id, float point, String title, String description, String materialCategoryId, String thumbnail, String filepath, String slug, String creatBy) {
        this.id = id;
        this.point = point;
        this.title = title;
        this.description = description;
        this.materialCategoryId = materialCategoryId;
        this.thumbnail = thumbnail;
        this.filepath = filepath;
        this.status = "active";
        this.slug = slug;
        this.creatBy = creatBy;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
    public void deleteMaterial(){
        this.status = "inactive";
        this.deletedAt = new Timestamp(System.currentTimeMillis());
    }
    public String getId() {
        return id;
    }

    public float getPoint() {
        return point;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMaterialCategoryId() {
        return materialCategoryId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getFilePath() {
        return filepath;
    }

    public String getStatus() {
        return status;
    }

    public String getSlug() {
        return slug;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String setFilePath() {
        return filepath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }
}
