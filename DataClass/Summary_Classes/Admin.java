package Summary_Classes;

import java.sql.Timestamp;
import java.util.List;

public class Admin extends User {
    private List<Provliege> provliege;
    private String adminToken;
    private Timestamp createdAt, deletedAt;

    public Admin(String fullName, String phone, String email, String password, String avatar, String adminToken,List<Provliege> provliege) {
        super(fullName, phone, email, password, avatar);
        this.provliege = provliege;
        this.adminToken = "ADMIN" + adminToken;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public List<Provliege> getPrivliege() {
        return provliege;
    }

    public void setPrivliege(List<Provliege> provliege) {
        this.provliege = provliege;
    }

    public String getAdminToken() {
        return adminToken;
    }

    @Override
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    @Override
    public Timestamp getCreatedAt() {
        return createdAt;
    }

}
