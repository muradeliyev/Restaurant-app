import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.PreparedStatement;

class Restaurant extends JFrame {
    App app;

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
    }

    Restaurant() {
        super("Restaurant");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        Welcome();
        setVisible(true);
    }

    void Welcome() {
        // this is the initial welcome page with log in option
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        welcomePanel.setOpaque(true);
        welcomePanel.setBorder(new EmptyBorder(30, 10, 10, 10));
//        welcomePanel.setBackground(Color.white);

        // Login header
        JLabel label = new JLabel("Log in");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        gbc.gridy = 0; welcomePanel.add(label, gbc);

        gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;

        // email label and textfield for logging in
        JLabel emaillabel = new JLabel("email");
        emaillabel.setFont(new Font("Arial", Font.BOLD, 12));
        emaillabel.setBorder(new EmptyBorder(30, 0, 5, 0));
        emaillabel.setForeground(Color.darkGray);
        gbc.gridy = 1; welcomePanel.add(emaillabel, gbc);
        JTextField email = new JTextField(20);
        email.setBorder(new EmptyBorder(10, 10, 10, 10));
        email.setOpaque(true); email.setBackground(Color.lightGray);
        gbc.gridy = 2; welcomePanel.add(email, gbc);


        // password label and textfield for logging in
        JLabel psswlabel = new JLabel("password");
        psswlabel.setFont(new Font("Arial", Font.BOLD, 12));
        psswlabel.setForeground(Color.darkGray);
        psswlabel.setBorder(new EmptyBorder(20, 0, 5, 0));
        gbc.gridy = 3; welcomePanel.add(psswlabel, gbc);
        JPasswordField password = new JPasswordField(20);
        password.setBorder(new EmptyBorder(10, 10, 10, 10));
        password.setOpaque(true); password.setBackground(Color.lightGray);
        gbc.gridy = 4; welcomePanel.add(password, gbc);


        // login button
        JButton login = new JButton("Log in");
        login.setHorizontalAlignment(SwingConstants.CENTER);
        login.setOpaque(true); login.setBackground(Color.darkGray); login.setForeground(Color.white);
        login.setFocusPainted(false); login.setRolloverEnabled(false);
        login.setBorder(new EmptyBorder(10, 0, 10, 0));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridy = 5; welcomePanel.add(login, gbc);

        // or sign in
        JLabel or = new JLabel("<html><h2>or</h2></html>");
        gbc.anchor = GridBagConstraints.CENTER; gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 6; welcomePanel.add(or, gbc);

        // basically signing in and creating new account are the same things
        JButton signin = new JButton("Create an account");
        signin.setHorizontalAlignment(SwingConstants.CENTER);
        signin.setOpaque(true); signin.setBackground(Color.darkGray); signin.setForeground(Color.white);
        signin.setFocusPainted(false); signin.setRolloverEnabled(false);
        signin.setBorder(new EmptyBorder(10, 0, 10, 0));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 7; welcomePanel.add(signin, gbc);

        add(welcomePanel);
    }
}
