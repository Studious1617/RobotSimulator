import javax.swing.*;
import java.awt.*;

public class CreateAccountGUI extends JFrame {
    // variables for the buttons and stuff
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    private JPanel mainPanel;
    private JTextField userName;
    private JLabel enterName;
    private JTextField userEmail;
    private JTextField userPass;
    private JTextField userConPass;
    private JPanel email;
    private JLabel enterPassword;
    private JLabel conPassword;
    private JLabel enterEmail;

    // has the basic/test GUI stuff
    CreateAccountGUI() {
        setTitle("HoneyComb Robo");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500,500));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
