
package Summary_Classes;

import java.io.*;
import java.util.*;
public class CategoryManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Category> categories;
    private static final String CATEGORIES_FILE = "categories.ser";
    
    public CategoryManager() {
        categories = loadCategories();
        if (categories.isEmpty()) {
            // Initialize with some default categories
            initializeDefaultCategories();
        }
    }
    
    private void initializeDefaultCategories() {
        categories.add(new Category("Dai So"));
        categories.add(new Category("Giai Tich"));
        categories.add(new Category("Tin Hoc Co So"));
        categories.add(new Category("Vat Li"));
        categories.add(new Category("Xac Suat Thong Ke"));
        saveCategories();
    }
    
    public void addCategory(Category category) {
        categories.add(category);
        saveCategories();
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void deleteMaterialInCategory(Category category,Material material){
        this.findCategoryByName(category.getName()).deleteMaterial(material);
        saveCategories();
    }

    public void addMaterialToCategory(Category category, Material newMaterial)
    {
        this.findCategoryByName(category.getName()).addMaterial(newMaterial);
        saveCategories();
    }
    
    public Category findCategoryByName(String name) {
        return categories.stream()
            .filter(cat -> cat.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    // Save categories to file
    public void saveCategories() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CATEGORIES_FILE))) {
            oos.writeObject(categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateMaterialListInCategory(Material material, Category category, List<Material> materials) {
        CategoryManager CM = new CategoryManager();
        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i).equals(material)) {
                materials.set(i, material);
                break;
            }
        }
        for (Category currentCategory:CM.getCategories())
            {
               if (currentCategory.equals(category))
               {
                   currentCategory.setMaterials(materials);
               }
            }
        CM.saveCategories();
        FileManager.saveMaterials(materials);
    }
    
    // Load categories from file
    @SuppressWarnings("unchecked")
    public List<Category> loadCategories() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("categories.ser"))) {
            return (List<Category>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

