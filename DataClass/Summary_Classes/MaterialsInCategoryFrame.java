package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MaterialsInCategoryFrame extends JFrame {
    public MaterialsInCategoryFrame(Category category, User user) {
        setTitle("Materials in " + category.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding xung quanh

        // Title label
        JLabel titleLabel = new JLabel("Materials in " + category.getName());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Khoảng cách dưới tiêu đề

        // Materials as buttons
        if (category.getMaterials().isEmpty()) {
            JLabel noMaterialsLabel = new JLabel("No materials in this category yet.");
            noMaterialsLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
            noMaterialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            noMaterialsLabel.setForeground(Color.GRAY);
            mainPanel.add(noMaterialsLabel);
        } else {
            for (Material material : category.getMaterials()) {
                JButton materialButton = new JButton(material.getTitle());
                materialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                materialButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
                materialButton.setBackground(new Color(46, 139, 87)); // Màu xanh lá cây nhạt
                materialButton.setForeground(Color.BLACK); // Chữ trắng
                materialButton.setFocusPainted(false); // Loại bỏ border focus
                materialButton.addActionListener(e -> {
                    new DetailsInMaterialFrame(material, category, user).setVisible(true);
                    dispose();
                });
                materialButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
                mainPanel.add(materialButton);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách giữa các nút
            }
        }

        // "Add Material" button
        JButton addMaterialButton = new JButton("Add Material");
        addMaterialButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addMaterialButton.setBackground(new Color(70, 130, 180)); // Màu xanh dương nhạt
        addMaterialButton.setForeground(Color.BLACK);
        addMaterialButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMaterialButton.setFocusPainted(false);
        addMaterialButton.addActionListener(e -> openAddMaterialFrame(category, user));
        mainPanel.add(addMaterialButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách dưới nút

        // "Back to Categories" button
        JButton backButton = new JButton("Back to Categories");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 20, 60)); // Màu đỏ nhạt
        backButton.setForeground(Color.BLACK);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            new CategorySelectionFrame(user).setVisible(true);
            dispose();
        });
        mainPanel.add(backButton);

        // Add scroll pane for main panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); // Cuộn mượt hơn
        add(scrollPane);
    }


    private void openAddMaterialFrame(Category category, User user) {
        JFrame addMaterialFrame = new JFrame("Add Material");
        addMaterialFrame.setSize(500, 400);
        addMaterialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addMaterialFrame.setLocationRelativeTo(this);

// Sử dụng GridBagLayout cho bố cục chính
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Thêm padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Đặt các thành phần lấp đầy theo chiều ngang
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần

// Nhãn và trường nhập liệu - Tiêu đề
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.weightx = 0.3; // Trọng số cho cột đầu tiên nhỏ hơn
        mainPanel.add(titleLabel, gbc);

        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(350, 30)); // Đặt kích thước cố định
        gbc.gridx = 1; // Cột thứ hai
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.weightx = 0.7; // Trọng số cho cột thứ hai lớn hơn
        mainPanel.add(titleField, gbc);

// Nhãn và trường nhập liệu - Mô tả
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 1; // Hàng thứ hai
        gbc.weightx = 0.3; // Trọng số cột đầu tiên
        mainPanel.add(descriptionLabel, gbc);

        JTextField descriptionField = new JTextField();
        descriptionField.setPreferredSize(new Dimension(350, 30)); // Đặt kích thước cố định
        gbc.gridx = 1; // Cột thứ hai
        gbc.gridy = 1; // Hàng thứ hai
        gbc.weightx = 0.7; // Trọng số cột thứ hai
        mainPanel.add(descriptionField, gbc);

// Nhãn và trường nhập liệu - URL
        JLabel downloadUrlLabel = new JLabel("Download URL:");
        downloadUrlLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 2; // Hàng thứ ba
        gbc.weightx = 0.3; // Trọng số cột đầu tiên
        mainPanel.add(downloadUrlLabel, gbc);

        JTextField downloadUrlField = new JTextField();
        downloadUrlField.setPreferredSize(new Dimension(350, 30)); // Đặt kích thước cố định
        gbc.gridx = 1; // Cột thứ hai
        gbc.gridy = 2; // Hàng thứ ba
        gbc.weightx = 0.7; // Trọng số cột thứ hai
        mainPanel.add(downloadUrlField, gbc);

        JButton addButton = new JButton("Add Material");
        addButton.setBackground(new Color(46, 139, 87));
        addButton.setForeground(Color.BLACK);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
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
                    user.addMaterial(currentCategory, newMaterial);
                }
                // Update Material globally
                FileManager.addMaterialToFile(newMaterial);

                JOptionPane.showMessageDialog(addMaterialFrame,
                        "Material added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                addMaterialFrame.dispose();
                // Refresh and reload the main frame
                categoryManager = new CategoryManager();
                currentCategory = categoryManager.findCategoryByName(category.getName());
                MaterialsInCategoryFrame.this.dispose(); // Close current frame
                new MaterialsInCategoryFrame(currentCategory, user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(addMaterialFrame,
                        "Please fill in all fields!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Căn giữa nút
        mainPanel.add(addButton, gbc);

        addMaterialFrame.add(mainPanel);
        addMaterialFrame.setVisible(true);
    }

}
