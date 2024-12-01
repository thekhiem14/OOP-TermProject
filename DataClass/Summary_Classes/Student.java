
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author 24hph
 */
public class Student extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    
    public Student(String email, String password, String name, String studentId, boolean isSuperAdmin) {
        super(email, password, name, isSuperAdmin);
        this.studentId = studentId;
    }
    
    @Override
    public boolean login(String password) {
        return this.getPassword().equals(password);
    }
    
    public String getStudentId() {
        return studentId;
    }
    
}
