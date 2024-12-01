package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminFrame extends JFrame {
    private Admin currentAdmin;

    public AdminFrame(User user) {
        this.currentAdmin = (Admin) user;

        // Frame properties
        setTitle("Admin Panel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Header label
        JLabel headerLabel = new JLabel("Admin Panel");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Category button
        JButton addCategoryButton = createStyledButton("Add Category");
        addCategoryButton.addActionListener(this::openAddCategoryFrame);

        // Show All Users button
        JButton showUsersButton = createStyledButton("Show All Users");
        showUsersButton.addActionListener(e -> openShowUsersFrame(e, user));

        // Show All Materials button
        JButton showMaterialsButton = createStyledButton("Show All Materials");
        showMaterialsButton.addActionListener(this::openShowMaterialsFrame);

        // Login as User button
        JButton loginAsUserButton = createStyledButton("Login as a User");
        loginAsUserButton.addActionListener(this::openLoginAsUserFrame);

        // Add components to the main panel
        mainPanel.add(headerLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
        mainPanel.add(addCategoryButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(showUsersButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(showMaterialsButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(loginAsUserButton);

        // Add main panel to the frame
        add(mainPanel);
    }

    // Utility method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(0, 102, 204)); // Blue color
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        return button;
    }

    // Method to open the Add Category frame
    private void openAddCategoryFrame(ActionEvent e) {
        JFrame addCategoryFrame = new JFrame("Add Category");
        addCategoryFrame.setSize(400, 300);
        addCategoryFrame.setLocationRelativeTo(this);
        addCategoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Category Name:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTextField categoryNameField = new JTextField();
        categoryNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(event -> {
            String categoryName = categoryNameField.getText().trim();
            if (!categoryName.isEmpty()) {
                this.currentAdmin.addCategory(categoryName);
                JOptionPane.showMessageDialog(addCategoryFrame, "Category added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                addCategoryFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addCategoryFrame, "Please enter a category name!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(categoryNameField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(addButton);

        addCategoryFrame.add(panel);
        addCategoryFrame.setVisible(true);
    }

    // Other frames (Show All Users and Show All Materials) follow the same approach
    private void openShowUsersFrame(ActionEvent e, User thisUser) {
        JFrame showUsersFrame = new JFrame("All Users");
        showUsersFrame.setSize(500, 400);
        showUsersFrame.setLocationRelativeTo(this);
        showUsersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Users List");
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<User> users = FileManager.loadUsers();
        DefaultListModel<String> userModel = new DefaultListModel<>();
        for (User user : users) {
            String x = "";
            if (user.isSuperAdmin()) x = "Admin";
            else x = "Student";
            userModel.addElement(user.getName() + " | " + user.getEmail() + " | " + x);
        }

        JList<String> userList = new JList<>(userModel);
        JScrollPane scrollPane = new JScrollPane(userList);

        JButton deleteButton = createStyledButton("Delete Selected User");
        deleteButton.addActionListener(event -> {
            int selectedIndex = userList.getSelectedIndex();
            if (selectedIndex != -1) {
                if(users.get(selectedIndex).getEmail().equals(thisUser.getEmail()))
                    JOptionPane.showMessageDialog(showUsersFrame, "Can't delete yourself!", "Error", JOptionPane.ERROR_MESSAGE);
                else{
                    users.remove(selectedIndex);
                    userModel.remove(selectedIndex);
                    FileManager.saveUsers(users);
                    JOptionPane.showMessageDialog(showUsersFrame, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(showUsersFrame, "No user selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(deleteButton);

        showUsersFrame.add(panel);
        showUsersFrame.setVisible(true);
    }
     // Method to open the Show All Materials frame
    private void openShowMaterialsFrame(ActionEvent e) {
    JFrame showMaterialsFrame = new JFrame("All Materials");
    showMaterialsFrame.setSize(500, 400);
    showMaterialsFrame.setLocationRelativeTo(this);
    showMaterialsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JLabel label = new JLabel("Materials List");
    label.setFont(new Font("SansSerif", Font.BOLD, 20));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Fetch all materials
    List<Material> materials = FileManager.loadMaterials();
    DefaultListModel<String> materialModel = new DefaultListModel<>();
    for (Material material : materials) {
        materialModel.addElement("Title: " + material.getTitle() + " | Author: " + material.getAuthor().getName());
    }

    JList<String> materialList = new JList<>(materialModel);
    JScrollPane scrollPane = new JScrollPane(materialList);

    JButton deleteButton = createStyledButton("Delete Selected Material");
    deleteButton.addActionListener(event -> {
        int selectedIndex = materialList.getSelectedIndex();
        if (selectedIndex != -1) {
            Material selectedMaterial = materials.get(selectedIndex);
            int confirmation = JOptionPane.showConfirmDialog(showMaterialsFrame,
                    "Are you sure you want to delete material: " + selectedMaterial.getTitle() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                materialModel.removeElementAt(selectedIndex);
                this.currentAdmin.deleteMaterial(materials, selectedMaterial); // Save changes
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

    panel.add(label);
    panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
    panel.add(scrollPane);
    panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
    panel.add(deleteButton);

    showMaterialsFrame.add(panel);
    showMaterialsFrame.setVisible(true);
    }

    // Similar improvements for openShowMaterialsFrame
    private void openLoginAsUserFrame(ActionEvent e) {
        new CategorySelectionFrame(this.currentAdmin).setVisible(true);
        dispose();
    }
}
