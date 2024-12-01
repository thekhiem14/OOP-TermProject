package Summary_Classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class DetailsInMaterialFrame extends JFrame {

    public DetailsInMaterialFrame(Material material, Category category, User user) {
        // Thiết lập frame
        setupFrame();

        // Panel chính
        JPanel mainPanel = createMainPanel();
        JPanel headerPanel = createHeaderPanel();
        JPanel detailsPanel = createDetailsPanel(material);
        JPanel footerPanel = createFooterPanel(category, user);

        // Thêm các phần vào panel chính
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Thêm panel chính vào frame
        add(mainPanel);
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Material Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        return mainPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel headerLabel = new JLabel("Material Details", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createDetailsPanel(Material material) {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        JLabel titleLabel = createStyledLabel("Title: " + material.getTitle(), labelFont);
        JLabel descriptionLabel = createStyledLabel("Description: " + material.getDescription(), labelFont);
        JLabel authorLabel = createStyledLabel("Author: " + material.getAuthor().getName(), labelFont);

        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(material.getUploadDate());
        JLabel uploadDateLabel = createStyledLabel("Uploaded on: " + formattedDate, labelFont);

        // URL dưới dạng hyperlink
        JLabel downloadUrlLabel = createStyledLabel("Download URL: ", labelFont);
        JLabel downloadUrlLink = createHyperlink(material.getDownloadUrl());

        JPanel urlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        urlPanel.setBackground(Color.WHITE);
        urlPanel.add(downloadUrlLabel);
        urlPanel.add(downloadUrlLink);

        // Thêm các thành phần vào panel
        detailsPanel.add(titleLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(descriptionLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(authorLabel);
        detailsPanel.add(Box.createVerticalStrut(10));
        detailsPanel.add(uploadDateLabel);
        detailsPanel.add(Box.createVerticalStrut(20));
        detailsPanel.add(urlPanel);

        return detailsPanel;
    }

    private JPanel createFooterPanel(Category category, User user) {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footerPanel.setBackground(new Color(240, 240, 240));

        JButton commentButton = createStyledButton("Comment", new Color(46, 139, 87));
        commentButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open comment frame!"));

        JButton ratingButton = createStyledButton("Rating", new Color(70, 130, 180));
        ratingButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open rating frame!"));

        JButton backButton = createStyledButton("Back", new Color(220, 20, 60));
        backButton.addActionListener(e -> {
            dispose();
            new MaterialsInCategoryFrame(category, user).setVisible(true);
        });

        footerPanel.add(commentButton);
        footerPanel.add(ratingButton);
        footerPanel.add(backButton);

        return footerPanel;
    }

    private JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private JLabel createHyperlink(String url) {
        JLabel hyperlink = new JLabel("<html><a href=''>" + url + "</a></html>");
        hyperlink.setForeground(Color.BLUE);
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        hyperlink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI(url));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unable to open link: " + ex.getMessage());
                }
            }
        });

        return hyperlink;
    }
}
