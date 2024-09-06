import javax.swing.*;

public class CreateAccountGUI extends JFrame {
    // variables for the buttons and stuff
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    // has the basic/test GUI stuff
    CreateAccountGUI() {
        frame.setSize(420, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);

        panel.setLayout(null);

        JLabel label = new JLabel("Users");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        frame.setVisible(true);
    }
}
