package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CommentFrame extends JFrame {
    private List<Comment> comments;
    private DefaultListModel<String> commentModel;
    private List<Material> materials = FileManager.loadMaterials();

    public CommentFrame(Material material, User user, Category category) {
        comments = material.getComments();
        commentModel = new DefaultListModel<>();

        if (comments.isEmpty()) {
            commentModel.addElement("No comments available.");
        } else {
            for (Comment comment : comments) {
                commentModel.addElement(comment.getAuthorUsername() + ": " + comment.getContent());
            }
        }

        setTitle("Comments of: " + material.getTitle());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Label
        JLabel titleLabel = new JLabel("Comments");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Comment List
        JList<String> commentList = new JList<>(commentModel);
        commentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(commentList);

        // Add Comment Button
        JButton addCommentButton = new JButton("Add Comment");
        addCommentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCommentButton.addActionListener((ActionEvent e) -> openAddCommentDialog(material, user, category));

        // Delete Comment Button
        JButton deleteCommentButton = new JButton("Delete Comment");
        deleteCommentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteCommentButton.addActionListener((ActionEvent e) -> deleteSelectedComment(commentList, material, user, category));

        // Add components to main panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(scrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(addCommentButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(deleteCommentButton);

        add(mainPanel);
    }

    // Open dialog to add a new comment
    private void openAddCommentDialog(Material material, User user, Category category) {
        JDialog addCommentDialog = new JDialog(this, "Add Comment", true);
        addCommentDialog.setSize(400, 200);
        addCommentDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input fields
        JTextField contentField = new JTextField();
        contentField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Add Comment Button
        JButton addButton = new JButton("Add");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(e -> {
            String author = user.getEmail();
            String content = contentField.getText().trim();
            if (!content.isEmpty()) {
                Comment newComment = new Comment(author, content);
                comments.add(newComment);
                commentModel.addElement(newComment.getAuthorUsername() + ": " + newComment.getContent());
                material.setComments(comments);

                updateMaterialList(material, category);
                addCommentDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(addCommentDialog, "Content cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("Content:"));
        panel.add(contentField);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        panel.add(addButton);

        addCommentDialog.add(panel);
        addCommentDialog.setVisible(true);
    }

    // Delete selected comment
    private void deleteSelectedComment(JList<String> commentList, Material material, User user, Category category) {
        int selectedIndex = commentList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this comment?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Comment selectedComment = comments.get(selectedIndex);
                if (selectedComment.getAuthorUsername().equals(user.getEmail()) || user.isSuperAdmin()) {
                    comments.remove(selectedIndex);
                    commentModel.remove(selectedIndex);

                    material.setComments(comments);
                    updateMaterialList(material,category);

                    if (comments.isEmpty()) {
                        commentModel.addElement("No comments available.");
                    }
                    JOptionPane.showMessageDialog(this, "Comment deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "You don't have permission to delete this comment!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a comment to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMaterialList(Material material, Category category) {
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
}
