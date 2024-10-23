package Summary_Classes;

import java.sql.Timestamp;

public class Admin extends User {
    private privilege privileges;
    private String adminToken;
    private Timestamp createdAt, deletedAt;

    public Admin(String fullName, String phone, String email, String password, String avatar, String adminToken,privilege privilege) {
        super(fullName, phone, email, password, avatar);
        this.privileges = privilege;
        this.adminToken = "ADMIN" + adminToken;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public privilege getprivilege() {
        return privileges;
    }

    public void setprivilege(privilege privilege) {
        this.privileges = privilege;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public Boolean hasPrivilege(String s) {
        
        if (this.getprivilege().getprivilege().contains(s)) return true;
        else return false;
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
