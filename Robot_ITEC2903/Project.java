import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Project {
    public static void main (String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(420,420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("Users");
        label.setBounds(10,20,80,25);
        panel.add(label);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);




        frame.setVisible(true);
    }
}
