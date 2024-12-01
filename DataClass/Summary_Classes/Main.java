/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Summary_Classes;
import javax.swing.*;
import java.util.*;

public class Main
{
    public static void main(String[] args){
        Admin a = new Admin("Khiem@", "123", "Khiem", true);
        List<User> list = FileManager.loadUsers();
        list.add(a);
        FileManager.saveUsers(list);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch login frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
}
}