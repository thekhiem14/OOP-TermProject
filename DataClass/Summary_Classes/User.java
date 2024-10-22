package Summary_Classes;
import java.sql.*;

public class User {
    private String fullName, phone, email, password, avatar, userToken, status;
    private Timestamp createdAt, deletedAt;

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public User(String fullName, String phone, String email, String password, String avatar) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.status = "active";
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public void deleteAccount(){
        this.status = "inactive";
        this.deletedAt = new Timestamp(System.currentTimeMillis());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }
}
