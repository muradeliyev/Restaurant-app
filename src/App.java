import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class App extends JFrame {
    GridBagConstraints gbc;

    App() {
        super("Restaurant");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        create();
        setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }

    void create() {
        gbc = new GridBagConstraints();

        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());
//        main.setBackground(Color.CYAN);
        main.setOpaque(true);

        gbc.gridy = 0; main.add(new JLabel("<html><h1 style='padding:10px;'>Welcome</h1></html>"), gbc);
        gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 1; main.add(new JLabel("<html><span style='text-align:left;width:100%;'>email</span></html>"), gbc);
        gbc.gridy = 2; main.add(new JTextField(10), gbc);

        gbc.gridy = 3; main.add(new JLabel("password"), gbc);
        gbc.gridy = 4; main.add(new JPasswordField(10), gbc);

        gbc.gridy = 5; main.add(new JButton("login"), gbc);
        gbc.gridy = 6; main.add(new JLabel("<html><i>or</i></html>"), gbc);
        gbc.gridy = 7; main.add(new JButton("create an account"), gbc);

        add(main);
    }
}
