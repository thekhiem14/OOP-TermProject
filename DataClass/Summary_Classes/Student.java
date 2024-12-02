
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;

public class Student extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    
    public Student(String email, String password, String name, String studentId, boolean isSuperAdmin) {
        super(email, password, name, isSuperAdmin);
        this.studentId = studentId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
}
