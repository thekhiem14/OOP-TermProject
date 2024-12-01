package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MaterialsInCategoryFrame extends JFrame {
    public MaterialsInCategoryFrame(Category category, User user) {
        setTitle("Materials in " + category.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add materials as buttons
        if (category.getMaterials().isEmpty()) {
            JLabel noMaterialsLabel = new JLabel("No materials in this category yet.");
            noMaterialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(noMaterialsLabel);
        } else {
            for (Material material : category.getMaterials()) {
                JButton materialButton = new JButton(material.getTitle());
                materialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                materialButton.addActionListener(e -> {new DetailsInMaterialFrame(material,category,user).setVisible(true);
                dispose();});
                mainPanel.add(materialButton);
            }
        }

        // Add "Add Material" button
        JButton addMaterialButton = new JButton("Add Material");
        addMaterialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMaterialButton.addActionListener(e -> openAddMaterialFrame(category, user));
        mainPanel.add(addMaterialButton);

        // Add back button
        JButton backButton = new JButton("Back to Categories");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            new CategorySelectionFrame(user).setVisible(true);
            dispose();
        });
        mainPanel.add(backButton);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
    }

    private void openAddMaterialFrame(Category category, User user) {
    JFrame addMaterialFrame = new JFrame("Add Material");
    addMaterialFrame.setSize(400, 300);
    addMaterialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    addMaterialFrame.setLocationRelativeTo(this);

    JPanel addPanel = new JPanel();
    addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField downloadUrlField = new JTextField();

    titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    descriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    downloadUrlField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    JButton addButton = new JButton("Add");
    addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    addButton.addActionListener(e -> {
        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String downloadUrl = downloadUrlField.getText().trim();

        if (!title.isEmpty() && !description.isEmpty() && !downloadUrl.isEmpty()) {
            Material newMaterial = new Material(title, description, downloadUrl, user);

            // Update Category using CategoryManager
            CategoryManager categoryManager = new CategoryManager();
            Category currentCategory = categoryManager.findCategoryByName(category.getName());
            if (currentCategory != null) {
                currentCategory.addMaterial(newMaterial);
                categoryManager.saveCategories();
            }
            // Update Material globally
            List<Material> materials = FileManager.loadMaterials();
            materials.add(newMaterial);
            FileManager.saveMaterials(materials);

            JOptionPane.showMessageDialog(addMaterialFrame, 
                "Material added successfully!", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE
            );

            addMaterialFrame.dispose();
            // Refresh and reload the main frame
            MaterialsInCategoryFrame.this.dispose(); // Close current frame
            new MaterialsInCategoryFrame(categoryManager.findCategoryByName(category.getName()), user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(addMaterialFrame, 
                "Please fill in all fields!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    });

    JButton backButton = new JButton("Back");
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.addActionListener(e -> {
        addMaterialFrame.dispose(); // Close Add Material frame
        MaterialsInCategoryFrame.this.setVisible(true); // Show main frame again
    });

    addPanel.add(new JLabel("Title:"));
    addPanel.add(titleField);
    addPanel.add(new JLabel("Description:"));
    addPanel.add(descriptionField);
    addPanel.add(new JLabel("Download URL:"));
    addPanel.add(downloadUrlField);
    addPanel.add(addButton);
    addPanel.add(backButton);

    addMaterialFrame.add(addPanel);

    // Hide current frame and show Add Material frame
    MaterialsInCategoryFrame.this.setVisible(false); // Hide current frame
    addMaterialFrame.setVisible(true); // Show Add Material frame
    }
}
