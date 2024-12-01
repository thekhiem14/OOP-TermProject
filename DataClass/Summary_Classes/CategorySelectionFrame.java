package Summary_Classes;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class CategorySelectionFrame extends JFrame {
    private CategoryManager categoryManager;
    private User currentUser;
    
    public CategorySelectionFrame(User user) {
        this.currentUser = user;
        this.categoryManager = new CategoryManager();
        
        setTitle("Educational Materials - Category Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Grid layout with 2 columns
        
        // Add categories as buttons
        for (Category category : categoryManager.getCategories()) {
            JButton categoryButton = new JButton(category.getName());
            categoryButton.setFont(new Font("Arial", Font.BOLD, 16));
            categoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openCategoryMaterials(category);
                }
            });
            mainPanel.add(categoryButton);
        }
        // Add scroll pane if there are many categories
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
    }
    
    private void openCategoryMaterials(Category category) {
        // Open a new frame to show materials in this category
        MaterialsInCategoryFrame materialsFrame = new MaterialsInCategoryFrame(category, currentUser);
        materialsFrame.setVisible(true);
        this.dispose(); // Close current frame
    }
}