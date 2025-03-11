
package Summary_Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private List<Material> materials;
    
    public Category(String name) {
        this.name = name;
        this.materials = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addMaterial(Material material) {
       if (material != null && !materials.contains(material)) materials.add(material);
    }
    
    public List<Material> getMaterials() {
        return materials;
    }
    
    public void setMaterials(List<Material> material){
        this.materials = material;
    }
    public void deleteMaterial(Material material)
    {
        materials.remove(material);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  
        }
        Category category = (Category) obj; 
        return name.equals(category.name);  
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();  
        result = 31 * result;
        return result;
    }
}
