/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
    
    public void addCategory(String categoryName)
    {
        CategoryManager categoryManager = new CategoryManager();
        categoryManager.addCategory(new Category(categoryName));
    }
    
    public void deleteUser(List<User> users, int selectedIndex)
    {
        users.remove(selectedIndex);
        FileManager.saveUsers(users);
    }
    
    public void deleteMaterial(List<Material> materials, Material selectedMaterial)
    {
        materials.remove(selectedMaterial); 
        CategoryManager CM = new CategoryManager();
        for (Category currentCategory:CM.getCategories())
        {
            List <Material> m = currentCategory.getMaterials();
            if (m.contains(selectedMaterial))
            {
                CM.deleteMaterialInCategory(currentCategory,selectedMaterial);
                break;
            }
        }
        CM.saveCategories();
        FileManager.saveMaterials(materials);
    }
    
    @Override
    public boolean login(String password) {
        return this.getPassword().equals(password);
    }
}
