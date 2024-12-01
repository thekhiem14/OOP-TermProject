package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DetailsInMaterialFrame extends JFrame {
    public DetailsInMaterialFrame(Material material, Category category, User user) {
        // Thiết lập frame
        setTitle("Material Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel chính (BorderLayout)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tạo panel chi tiết thông tin
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Title: " + material.getTitle());
        JLabel descriptionLabel = new JLabel("Description: " + material.getDescription());
        JLabel authorLabel = new JLabel("Author: " + material.getAuthor().getName());

        // Định dạng ngày tải lên thành chuỗi dễ đọc
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(material.getUploadDate());
        JLabel uploadDateLabel = new JLabel("Uploaded on: " + formattedDate);

        // Tạo panel ngang để chứa nhãn và trường URL
        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.X_AXIS));

        JLabel downloadUrlLabel = new JLabel("Download URL: ");
        JTextField downloadUrlField = new JTextField(material.getDownloadUrl());
        downloadUrlField.setEditable(false); // Không cho phép chỉnh sửa
        downloadUrlField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Thêm nhãn và trường vào panel ngang
        urlPanel.add(downloadUrlLabel);
        urlPanel.add(downloadUrlField);

        // Căn chỉnh các nhãn ở giữa
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm các thành phần vào panel chi tiết
        detailsPanel.add(titleLabel);
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(authorLabel);
        detailsPanel.add(uploadDateLabel);
        detailsPanel.add(urlPanel); // Thêm panel ngang vào

        // Thêm panel chi tiết vào giữa frame
        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        // Thêm nút "Back" ở trên cùng bên trái
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {dispose();
        new MaterialsInCategoryFrame(category,user).setVisible(true);});
        JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topLeftPanel.add(backButton);
        mainPanel.add(topLeftPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton commentButton = new JButton("Comment");
        commentButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Open comment frame!");
        });
        bottomPanel.add(commentButton, BorderLayout.WEST);

        JButton ratingButton = new JButton("Rating");
        ratingButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Open rating frame!");
        });
        bottomPanel.add(ratingButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Thêm panel chính vào frame
        setLayout(new BorderLayout());
        add(mainPanel);
    }
}
