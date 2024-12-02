package Summary_Classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class LoginFrame extends JFrame {
    private List<User> users;

    public LoginFrame() {
        // Load users from file
        users = FileManager.loadUsers();

        // Frame settings
        setTitle("Educational Materials Platform - Login");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Welcome Panel (Top)
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Educational Materials Platform", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(34, 139, 34));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(welcomePanel, BorderLayout.NORTH);

        // Main Login Panel (Center)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);
        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        loginPanel.add(emailField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                boolean checkUser = false;
                for (User user:users)
                {
                    if (user.getEmail().equals(email) && user.login(password))
                    {
                        LoginFrame.this.dispose();
                        if (user.isSuperAdmin()) {
                            checkUser = true;
                            new AdminFrame(user).setVisible(true);
                            break;
                        } else {
                            checkUser = true;
                            new CategorySelectionFrame(user).setVisible(true);
                        }
                    }
                }
                if (!checkUser){
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        add(loginPanel, BorderLayout.CENTER);

        // Footer Panel with Register Button
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        JLabel registerLabel = new JLabel("Don't have an account?", JLabel.CENTER);
        footerPanel.add(registerLabel, BorderLayout.NORTH);

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(0, 102, 204));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationFrame();
            }
        });
        footerPanel.add(registerButton, BorderLayout.CENTER);

        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(footerPanel, BorderLayout.SOUTH);

        // Set the frame visible
        setVisible(true);
    }

    private void openRegistrationFrame() {
        JFrame registrationFrame = new JFrame("Student Registration");
        registrationFrame.setSize(400, 300);
        registrationFrame.setLocationRelativeTo(null);

        JPanel registrationPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        registrationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        registrationPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        registrationPanel.add(emailField);

        registrationPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        registrationPanel.add(passwordField);

        registrationPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        registrationPanel.add(nameField);

        registrationPanel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        registrationPanel.add(studentIdField);

        JButton submitButton = new JButton("Register");
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(new Color(0, 102, 204));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String studentId = studentIdField.getText();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || studentId.isEmpty()) {
                    JOptionPane.showMessageDialog(registrationFrame,
                            "All fields are required!",
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<User> users = FileManager.loadUsers();
                boolean error = false;
                for (User user:users){
                    if (user.getEmail().equals(email)) {
                        error = true;
                        break;
                    }
                }

                if(error){
                    JOptionPane.showMessageDialog(registrationFrame,
                            "Email has been registered!",
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Student newStudent = new Student(email, password, name, studentId, false);
                FileManager.addUserToFile(users, newStudent);

                JOptionPane.showMessageDialog(registrationFrame,
                        "Registration Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                registrationFrame.dispose();
            }
        });
        registrationPanel.add(submitButton);

        registrationFrame.add(registrationPanel);
        registrationFrame.setVisible(true);
    }
}
