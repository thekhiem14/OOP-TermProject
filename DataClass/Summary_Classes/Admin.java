/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author 24hph
 */
public class Admin extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public Admin(String email, String password, String name, boolean isSuperAdmin) {
        super(email, password, name, isSuperAdmin);
    }
    
    @Override
    public boolean login(String password) {
        return this.getPassword().equals(password);
    }
}