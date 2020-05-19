import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Restaurant extends JFrame {
    static ArrayList<CustomerAccount> customers;
    static ArrayList<AdminAccount> admins;

    final int WITDTH = 300;
    final int HEIGHT = 500;

    GridBagConstraints maingbc = new GridBagConstraints(), rootgbc = new GridBagConstraints();
    JPanel root;
    ButtonUI defaultUI;

    public static void main(String[] args) {
        customers = new ArrayList<CustomerAccount>();
        admins = new ArrayList<AdminAccount>();

        Restaurant restaurant = new Restaurant();
    }

    Restaurant() {
        super("Restaurant");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WITDTH, HEIGHT);
        root = new JPanel();
        root.setLayout(new GridBagLayout());
        root.setOpaque(true); root.setBackground(Color.white);
//        maingbc.gridx = 1; maingbc.gridy = 1;
        maingbc.weightx = 1.0; maingbc.weighty = 1.0;
        maingbc.fill = GridBagConstraints.BOTH;
        add(root, maingbc);
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
        welcomePanel.setBackground(Color.white);
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
        Button login = new Button("Log in");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridy = 5; welcomePanel.add(login, gbc);
        defaultUI = login.getUI(); // trying to not write the same code over and over again

        // or sign in
        JLabel or = new JLabel("<html><h2>or</h2></html>");
        gbc.anchor = GridBagConstraints.CENTER; gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 6; welcomePanel.add(or, gbc);

        // basically signing in and creating new account are the same things
        Button signin = new Button("Create an account");
        gbc.fill = GridBagConstraints.HORIZONTAL;

        signin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                root.remove(welcomePanel);
                root.updateUI();
                customerOrAdmin();
            }
        });
        gbc.gridy = 7; welcomePanel.add(signin, gbc);

        root.add(welcomePanel, rootgbc);
    }
    void customerOrAdmin() {
        // i created two main components:
        // container and back_button
        JPanel container = new JPanel(new GridBagLayout());
        container.setSize(100, 50);
        container.setOpaque(true);
        container.setBackground(Color.white);

        JRadioButton customer = new JRadioButton("Customer", true);
        customer.setOpaque(true); customer.setBackground(Color.white);
        JRadioButton admin = new JRadioButton("Admin");
        admin.setOpaque(true); admin.setBackground(Color.white);

        ButtonGroup group = new ButtonGroup();
        group.add(customer);
        group.add(admin);

        Button create = new Button("Create");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; container.add(customer, gbc);
        gbc.insets = new Insets(10, 0, 30, 0);
        gbc.gridy = 1; container.add(admin, gbc);
        gbc.gridy = 2; container.add(create, gbc);


        // adding back button
        Button back = new Button("<", Color.white, Color.darkGray);
        back.setFont(new Font("Arial", Font.BOLD, 16));
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                root.remove(container);
                root.remove(back);
                root.updateUI();
                Welcome();
            }
        });

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        root.add(back, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        root.add(container, gbc);

    }
}
