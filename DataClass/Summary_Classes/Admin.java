package Summary_Classes;

import java.sql.Timestamp;
import java.util.List;

public class Admin extends User {
    private List<Privliege> provliege;
    private String adminToken;
    private Timestamp createdAt, deletedAt;

    public Admin(String fullName, String phone, String email, String password, String avatar, String adminToken,List<Privliege> provliege) {
        super(fullName, phone, email, password, avatar);
        this.provliege = provliege;
        this.adminToken = "ADMIN" + adminToken;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public List<Privliege> getPrivliege() {
        return provliege;
    }

    public void setPrivliege(List<Privliege> provliege) {
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
