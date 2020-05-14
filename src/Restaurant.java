import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.PreparedStatement;

class Restaurant {
    App app;

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
    }

    Restaurant() {
        app = new App();
        Welcome();
        app.setVisible(true);
    }

    void Welcome() {
        // this is the initial welcome page with log in option
        JPanel welcomePanel = new JPanel();
        // applying vertical alignment for items
        welcomePanel.setLayout(new GridLayout(6, 1));

        welcomePanel.setOpaque(true);
        welcomePanel.setBorder(new EmptyBorder(30, 10, 10, 10));
//        welcomePanel.setBackground(Color.black);

        // Login header
        JLabel label = new JLabel("Log in");
        label.setHorizontalAlignment(SwingConstants.CENTER);
//        label.setBorder(new EmptyBorder(0, 0, 25, 0));
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setOpaque(true);
//        label.setBackground(Color.RED);

        // email label and textfield for logging in
        JLabel emaillabel = new JLabel("email");
        emaillabel.setBorder(new EmptyBorder(20, 0, 5, 0));
        JTextField email = new JTextField(20);

        // password label and textfield for logging in
        JLabel psswlabel = new JLabel("password");
        psswlabel.setBorder(new EmptyBorder(20, 0, 5, 0));
        JTextField password = new JTextField(20);

        welcomePanel.add(label);

        welcomePanel.add(emaillabel);
        welcomePanel.add(email);

        welcomePanel.add(psswlabel);
        welcomePanel.add(password);
        app.add(welcomePanel);
    }
}
