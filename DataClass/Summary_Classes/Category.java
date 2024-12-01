/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Summary_Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 24hph
 */
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
        materials.add(material);
    }
    
    public List<Material> getMaterials() {
        return materials;
    }
}
