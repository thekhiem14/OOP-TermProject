package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminFrame extends JFrame {
    private User currentAdmin;
    public AdminFrame(User user) {
        this.currentAdmin = user;
        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add Category button
        JButton addCategoryButton = new JButton("Add Category");
        addCategoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCategoryButton.addActionListener(this::openAddCategoryFrame);

        // Show All Users button
        JButton showUsersButton = new JButton("Show All Users");
        showUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showUsersButton.addActionListener(this::openShowUsersFrame);

        // Show All Materials button
        JButton showMaterialsButton = new JButton("Show All Materials");
        showMaterialsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showMaterialsButton.addActionListener(this::openShowMaterialsFrame);
        
        JButton loginAsUserButton = new JButton("Login as an User");
        loginAsUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginAsUserButton.addActionListener(this::openLoginAsUserFrame);

        // Add buttons to the main panel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(addCategoryButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(showUsersButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(showMaterialsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(loginAsUserButton);

        add(mainPanel);
    }

    // Method to open the Add Category frame
    private void openAddCategoryFrame(ActionEvent e) {
        JFrame addCategoryFrame = new JFrame("Add Category");
        addCategoryFrame.setSize(300, 200);
        addCategoryFrame.setLocationRelativeTo(this);
        addCategoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField categoryNameField = new JTextField();
        categoryNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton addButton = new JButton("Add");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(event -> {
            String categoryName = categoryNameField.getText().trim();
            if (!categoryName.isEmpty()) {
                CategoryManager categoryManager = new CategoryManager();
                categoryManager.addCategory(new Category(categoryName));
                JOptionPane.showMessageDialog(addCategoryFrame, "Category added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                addCategoryFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addCategoryFrame, "Please enter a category name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("Category Name:"));
        panel.add(categoryNameField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        panel.add(addButton);

        addCategoryFrame.add(panel);
        addCategoryFrame.setVisible(true);
    }

    // Method to open the Show All Users frame
    private void openShowUsersFrame(ActionEvent e) {
    JFrame showUsersFrame = new JFrame("All Users");
    showUsersFrame.setSize(400, 300);
    showUsersFrame.setLocationRelativeTo(this);
    showUsersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // Fetch all users
    List<User> users = FileManager.loadUsers(); 
    DefaultListModel<String> userModel = new DefaultListModel<>();
    for (User user : users) {
        String displayInfo = "Name: " + user.getName() + " | Email: " + user.getEmail();
        userModel.addElement(displayInfo);
    }

    JList<String> userList = new JList<>(userModel);
    userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(userList);

    JButton deleteButton = new JButton("Delete");
    deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    deleteButton.addActionListener(event -> {
        int selectedIndex = userList.getSelectedIndex();
        if (selectedIndex != -1) {
            User selectedUser = users.get(selectedIndex);
            int confirmation = JOptionPane.showConfirmDialog(showUsersFrame,
                    "Are you sure you want to delete user: " + selectedUser.getName() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                users.remove(selectedUser); 
                userModel.remove(selectedIndex); // Update UI
                FileManager.saveUsers(users); // Save changes
                JOptionPane.showMessageDialog(showUsersFrame,
                        "User deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(showUsersFrame,
                    "Please select a user to delete!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    });

    panel.add(scrollPane);
    panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
    panel.add(deleteButton);

    showUsersFrame.add(panel);
    showUsersFrame.setVisible(true);
}


    // Method to open the Show All Materials frame
    private void openShowMaterialsFrame(ActionEvent e) {
    JFrame showMaterialsFrame = new JFrame("All Materials");
    showMaterialsFrame.setSize(400, 300);
    showMaterialsFrame.setLocationRelativeTo(this);
    showMaterialsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // Fetch all materials
    List<Material> materials = FileManager.loadMaterials(); 
    DefaultListModel<String> materialModel = new DefaultListModel<>();
    for (Material material : materials) {
        String displayInfo = "Name: " + material.getTitle() + " | Author: " + material.getAuthor().getName();
        materialModel.addElement(displayInfo);
    }

    JList<String> materialList = new JList<>(materialModel);
    materialList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane = new JScrollPane(materialList);

    JButton deleteButton = new JButton("Delete");
    deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    deleteButton.addActionListener(event -> {
       int selectedIndex = materialList.getSelectedIndex();
        if (selectedIndex != -1) {
            Material selectedMaterial = materials.get(selectedIndex);
            int confirmation = JOptionPane.showConfirmDialog(showMaterialsFrame,
                    "Are you sure you want to delete material: " + selectedMaterial.getTitle() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                materials.remove(selectedMaterial); 
                materialModel.removeElement(selectedMaterial); // Update UI
                FileManager.saveMaterials(materials); // Save changes
                JOptionPane.showMessageDialog(showMaterialsFrame,
                        "Material deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(showMaterialsFrame,
                    "Please select a material to delete!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    });

    panel.add(scrollPane);
    panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
    panel.add(deleteButton);

    showMaterialsFrame.add(panel);
    showMaterialsFrame.setVisible(true);
    }

    private void openLoginAsUserFrame(ActionEvent e) 
    {
        new CategorySelectionFrame(this.currentAdmin).setVisible(true);
        dispose(); // Close login frame
    }
}
