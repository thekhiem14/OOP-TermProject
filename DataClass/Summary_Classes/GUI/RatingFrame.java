package Summary_Classes.GUI;

import Summary_Classes.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RatingFrame extends JFrame {
    private List<Rating> ratings;
    private List<Material> materials = FileManager.loadMaterials();

    public RatingFrame(Material material, User user, Category category) {
        ratings = material.getRatings();

        setTitle("Ratings for: " + material.getTitle());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Average Rating");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Average Rating Label
        JLabel averageLabel = new JLabel(ratings.isEmpty() ?
                "No ratings available." :
                String.format("Average Rating: %.2f/10", calculateAverageRating()));
        averageLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        averageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Add Button
        JButton addButton = new JButton("Add Rating");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(e -> openAddRatingDialog(material, user, averageLabel, category));

        // Delete Button
        JButton deleteButton = new JButton("Delete Rating");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.addActionListener(e -> deleteSelectedRating(ratings, material, user, averageLabel, category));

        // Add components to panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(averageLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(addButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(deleteButton);

        add(mainPanel);
    }

    // Open dialog to add a new rating
    private void openAddRatingDialog(Material material, User user, JLabel averageLabel, Category category) {
    // Kiểm tra xem người dùng đã đánh giá chưa
    for (Rating rating : ratings) {
        if (rating.getRaterUsername().equals(user.getEmail())) {
            JOptionPane.showMessageDialog(this,
                    "You have already rated this material.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Thoát ra, không mở hộp thoại thêm đánh giá
        }
    }

    JDialog addRatingDialog = new JDialog(this, "Add Rating", true);
    addRatingDialog.setSize(300, 200);
    addRatingDialog.setLocationRelativeTo(this);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Rating Input Field
    JSpinner ratingSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
    ratingSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    JButton addButton = new JButton("Add");
    addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    addButton.addActionListener(e -> {
        CategoryManager CM = new CategoryManager();
        int score = (int) ratingSpinner.getValue();
        Rating newRating = new Rating(user.getEmail(), score);
        user.addRatingToMaterial(newRating, material);
        CM.updateMaterialListInCategory(material, category, materials);
        averageLabel.setText(String.format("Average Rating: %.2f/10", calculateAverageRating())); // Cập nhật điểm trung bình
        addRatingDialog.dispose();
    });

    panel.add(new JLabel("Score (1-10):"));
    panel.add(ratingSpinner);
    panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
    panel.add(addButton);

    addRatingDialog.add(panel);
    addRatingDialog.setVisible(true);
}

   // Delete selected rating
private void deleteSelectedRating(List<Rating> ratings, Material material, User user, JLabel averageLabel, Category category) {
    boolean found = false;
    
    // Tìm và xóa rating của người dùng hiện tại
    for (int i = 0; i < ratings.size(); i++) {
        if (ratings.get(i).getRaterUsername().equals(user.getEmail())) {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete your rating?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                CategoryManager CM = new CategoryManager();
                Rating yourRating = ratings.get(i);
                ratings.remove(yourRating);
                user.deleteRatingInMaterial(yourRating, material);
                CM.updateMaterialListInCategory(material, category, materials); // Cập nhật dữ liệu
                averageLabel.setText(ratings.isEmpty() ?
                        "No ratings available." :
                        String.format("Average Rating: %.2f/10", calculateAverageRating()));
                JOptionPane.showMessageDialog(this, "Your rating has been deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                found = true;
            }
            break;
        }
    }

    // Nếu không tìm thấy đánh giá của người dùng
    if (!found) {
        JOptionPane.showMessageDialog(this, "You haven't rated this material yet!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    // Calculate average rating
    private double calculateAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream().mapToInt(Rating::getScore).average().orElse(0.0);
    }
}

