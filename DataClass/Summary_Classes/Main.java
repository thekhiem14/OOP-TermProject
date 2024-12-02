package Summary_Classes;

import Summary_Classes.GUI.LoginFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args){
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