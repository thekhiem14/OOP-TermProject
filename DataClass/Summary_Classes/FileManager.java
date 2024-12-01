
package Summary_Classes;
import java.util.*;
import java.io.*;
/**
 *
 * @author 24hph
 */
public class FileManager {
    private static final String USERS_FILE = "users.ser";
    private static final String MATERIALS_FILE = "materials.ser";
    
    // Save users to file
    public static void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
    
    // Load users from file
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing users file found. Starting with empty user list.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // Save materials to file
    public static void saveMaterials(List<Material> materials) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MATERIALS_FILE))) {
            oos.writeObject(materials);

            System.out.println("Materials saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving materials: " + e.getMessage());
        }
    }
    
    // Load materials from file
    @SuppressWarnings("unchecked")
    public static List<Material> loadMaterials() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MATERIALS_FILE))) {
            return (List<Material>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing materials file found. Starting with empty material list.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading materials: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
