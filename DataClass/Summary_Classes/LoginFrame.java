/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Summary_Classes;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author 24hph
 */
public class LoginFrame extends JFrame {
    private List<User> users;
    
    public LoginFrame() {
        // Load users from file
        users = FileManager.loadUsers();
        
        setTitle("Educational Materials Platform - Login");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        loginPanel.add(new JLabel("Username:"));
        JTextField emailField = new JTextField();
        loginPanel.add(emailField);
        
        loginPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @SuppressWarnings("unused")
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                
                User user = authenticateUser(email, password);
                if (user.isSuperAdmin())
                {
                    new AdminFrame(user).setVisible(true);
                    dispose();
                }
                else if (user != null) {
                    // Open category selection frame
                    new CategorySelectionFrame(user).setVisible(true);
                    dispose(); // Close login frame
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "Invalid username or password", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        loginPanel.add(loginButton);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationFrame();
            }
        });
        loginPanel.add(registerButton);
        
        add(loginPanel);
    }
    
    private User authenticateUser(String email, String password) {
        return users.stream()
            .filter(user -> user.getEmail().equals(email) && user.login(password))
            .findFirst()
            .orElse(null);
    }
    
    private void openRegistrationFrame() {
        // Create a simple registration frame
        JFrame registrationFrame = new JFrame("Student Registration");
        registrationFrame.setSize(300, 250);
        registrationFrame.setLocationRelativeTo(null);
        
        JPanel registrationPanel = new JPanel(new GridLayout(5, 2));
        
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
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String name = new String(nameField.getText());
                String studentId = studentIdField.getText();
                
                // Basic validation
                if (email.isEmpty() || password.isEmpty() || studentId.isEmpty()) {
                    JOptionPane.showMessageDialog(registrationFrame, 
                        "All fields are required", 
                        "Registration Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                // Create new student
                Student newStudent = new Student(email, password, name, studentId, false);
                
                // Save to users list and file
                List<User> users = FileManager.loadUsers();
                users.add(newStudent);
                FileManager.saveUsers(users);
                LoginFrame.this.dispose();
                new LoginFrame().setVisible(true);
                
                JOptionPane.showMessageDialog(registrationFrame, 
                    "Registration Successful!", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                registrationFrame.dispose();
            }
        });
        registrationPanel.add(submitButton);
        
        registrationFrame.add(registrationPanel);
        registrationFrame.setVisible(true);
    }

}