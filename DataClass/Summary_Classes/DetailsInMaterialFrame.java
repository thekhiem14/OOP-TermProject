package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DetailsInMaterialFrame extends JFrame {
    public DetailsInMaterialFrame(Material material, Category category, User user) {
        // Thiết lập frame
        setTitle("Material Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính (BorderLayout)
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel headerLabel = new JLabel("Material Details");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Panel chứa thông tin chi tiết
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Title: " + material.getTitle());
        JLabel descriptionLabel = new JLabel("Description: " + material.getDescription());
        JLabel authorLabel = new JLabel("Author: " + material.getAuthor().getName());

        // Định dạng ngày tải lên thành chuỗi dễ đọc
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(material.getUploadDate());
        JLabel uploadDateLabel = new JLabel("Uploaded on: " + formattedDate);

        // Tạo panel ngang để chứa nhãn và trường URL
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BorderLayout());
        urlPanel.setBackground(Color.WHITE);
        JLabel downloadUrlLabel = new JLabel("Download URL:");
        JTextField downloadUrlField = new JTextField(material.getDownloadUrl());
        downloadUrlField.setEditable(false); // Không cho phép chỉnh sửa
        downloadUrlField.setBackground(Color.LIGHT_GRAY);
        downloadUrlField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        urlPanel.add(downloadUrlLabel, BorderLayout.WEST);
        urlPanel.add(downloadUrlField, BorderLayout.CENTER);

        // Định dạng phông chữ và căn chỉnh
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        titleLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);
        authorLabel.setFont(labelFont);
        uploadDateLabel.setFont(labelFont);

        // Căn chỉnh các nhãn ở giữa
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        uploadDateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Thêm các thành phần vào panel chi tiết
        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các thành phần
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(authorLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(uploadDateLabel);
        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(urlPanel);

        // Thêm nút "Back"
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 20, 60)); // Crimson
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            dispose();
            new MaterialsInCategoryFrame(category, user).setVisible(true);
        });
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.setBackground(new Color(240, 240, 240));
        topLeftPanel.add(backButton);

        // Footer panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBackground(new Color(240, 240, 240));

        JButton commentButton = new JButton("Comment");
        commentButton.setFont(new Font("Arial", Font.BOLD, 14));
        commentButton.setBackground(new Color(46, 139, 87)); // Green
        commentButton.setForeground(Color.WHITE);
        commentButton.setFocusPainted(false);
        commentButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open comment frame!"));
        footerPanel.add(commentButton);

        JButton ratingButton = new JButton("Rating");
        ratingButton.setFont(new Font("Arial", Font.BOLD, 14));
        ratingButton.setBackground(new Color(70, 130, 180)); // Steel blue
        ratingButton.setForeground(Color.WHITE);
        ratingButton.setFocusPainted(false);
        ratingButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open rating frame!"));
        footerPanel.add(ratingButton);

        // Thêm các phần vào frame
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
