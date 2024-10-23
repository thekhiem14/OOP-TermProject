package Summary_Classes;

import java.sql.Timestamp;
import java.util.List;

public class Admin extends User {
    private List<privilege> privilege;
    private String adminToken;
    private Timestamp createdAt, deletedAt;

    public Admin(String fullName, String phone, String email, String password, String avatar, String adminToken,List<privilege> privilege) {
        super(fullName, phone, email, password, avatar);
        this.privilege = privilege;
        this.adminToken = "ADMIN" + adminToken;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public List<privilege> getprivilege() {
        return privilege;
    }

    public void setprivilege(List<privilege> privilege) {
        this.privilege = privilege;
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
