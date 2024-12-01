
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author 24hph
 */
public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private String name;
    private boolean isSuperAdmin;
    
    public User(String email, String password, String name, boolean isSuperAdmin) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isSuperAdmin = isSuperAdmin;
    }
    
    // Getters and setters
    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getName()
    {
        return name;
    }

    // Abstract login method
    public abstract boolean login(String password);
}