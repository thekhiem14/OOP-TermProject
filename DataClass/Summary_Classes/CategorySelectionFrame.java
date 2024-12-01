package Summary_Classes;

import javax.swing.*;
import java.awt.*;

public class CategorySelectionFrame extends JFrame {
    private CategoryManager categoryManager;
    private User currentUser;

    public CategorySelectionFrame(User user) {
        this.currentUser = user;
        this.categoryManager = new CategoryManager();

        // Frame settings
        setTitle("Educational Materials - Category Selection");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel headerLabel = new JLabel("Choose a Category");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 15, 15)); // Grid layout with spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Add categories as buttons
        for (Category category : categoryManager.getCategories()) {
            JButton categoryButton = new JButton(category.getName());
            categoryButton.setFont(new Font("Arial", Font.BOLD, 18));
            categoryButton.setBackground(new Color(46, 139, 87)); // Green
            categoryButton.setForeground(Color.BLACK);
            categoryButton.setFocusPainted(false);
            categoryButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            categoryButton.addActionListener(e -> openCategoryMaterials(category));
            mainPanel.add(categoryButton);
        }

        // Scroll pane for main panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Footer panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.BLACK);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            new LoginFrame().setVisible(true); // Return to login
            dispose();
        });
        footerPanel.add(backButton);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Make frame visible
        setVisible(true);
    }

    private void openCategoryMaterials(Category category) {
        // Open a new frame to show materials in this category
        MaterialsInCategoryFrame materialsFrame = new MaterialsInCategoryFrame(category, currentUser);
        materialsFrame.setVisible(true);
        this.dispose(); // Close current frame
    }
}