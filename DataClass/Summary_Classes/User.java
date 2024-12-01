
package Summary_Classes;

import java.io.Serial;
import java.io.Serializable;

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
    
    public void addMaterial(Category currentCategory, Material newMaterial)
    {
        CategoryManager categoryManager = new CategoryManager();
        currentCategory.addMaterial(newMaterial);
        categoryManager.saveCategories();
    }
    public void addCommentToMaterial(Comment newComment,Material material)
    {
        material.addComments(newComment);
    }
    
    public void deleteCommentInMaterial(Comment selectedComment, Material material)
    {
        material.deleteComments(selectedComment);
    }
    
    public void addRatingToMaterial(Rating newRating, Material material)
    {
        material.addRating(newRating);
    }
    
    public void deleteRatingInMaterial(Rating yourRating, Material material)
    {
        material.deleteRating(yourRating);
    }
    // Abstract login method
    public abstract boolean login(String password);
}
