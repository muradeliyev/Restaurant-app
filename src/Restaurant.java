import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Restaurant extends JFrame {
    DataContainer data = new DataContainer();
    Color invalidBackground = new Color(255, 70, 80);

    final int WITDTH = 400;
    final int HEIGHT = 600;

    GridBagConstraints maingbc = new GridBagConstraints(), rootgbc = new GridBagConstraints();
    JPanel root;

    public static void main(String[] args) {
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
        JTextField email = new Entry(20);
        gbc.gridy = 1;
        welcomePanel.add(new Label("email"), gbc);
        gbc.gridy = 2;
        welcomePanel.add(email, gbc);

        // password label and textfield for logging in
        gbc.gridy = 3; welcomePanel.add(new Label("password"), gbc);

        JPasswordField password = new JPasswordField(20);
        password.setBorder(new EmptyBorder(10, 10, 10, 10));
        password.setOpaque(true); password.setBackground(Color.lightGray);
        gbc.gridy = 4; welcomePanel.add(password, gbc);


        // login button
        Button login = new Button("Log in");
        login.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridy = 5; welcomePanel.add(login, gbc);

        // or sign in
        JLabel or = new JLabel("<html><h2>or</h2></html>");
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
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

        JRadioButton customer, admin;

        customer = new JRadioButton("Customer", true);
        customer.setOpaque(true); customer.setBackground(Color.white);

        admin = new JRadioButton("Admin");
        admin.setOpaque(true); admin.setBackground(Color.white);

        ButtonGroup group = new ButtonGroup();
        group.add(customer);
        group.add(admin);

        Button create = new Button("Create");

        GridBagConstraints gbc = new GridBagConstraints();

//        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0; container.add(customer, gbc);
        gbc.insets = new Insets(10, 0, 30, 0);
        gbc.gridy = 1; container.add(admin, gbc);
        gbc.gridy = 2; container.add(create, gbc);


        // adding back button
        Button back = new Button("<", Color.white, Color.darkGray);
        back.setToolTipText("Go back");
        back.setFont(new Font("Arial", Font.BOLD, 25));

        create.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                root.remove(container);
                root.remove(back);
                root.updateUI();
                signIn(admin.isSelected()?"administrator":"customer");
            }
        });

        back.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                root.remove(container);
                root.remove(back);
                root.updateUI();
                Welcome();
            }
        });

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        root.add(back, gbc);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        root.add(container, gbc);
    }
    void signIn(String as) {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground(Color.white);
        container.setOpaque(true);
        GridBagConstraints gbc = new GridBagConstraints();

        // Signin header
        JLabel label = new JLabel("Sign in as " + as);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        container.add(label, gbc);

        gbc.weightx = 1.0; gbc.anchor = GridBagConstraints.WEST;

        Entry email = new Entry(20);
        gbc.gridy = 1;
        container.add(new Label("email"), gbc);
        gbc.gridy = 2;
        container.add(email, gbc);

        Entry username = new Entry(20);
//        username.addActionListener(new ActionListener() {
//            @Override public void actionPerformed(ActionEvent e) {
//                System.out.println("something");
//            }
//        });
        username.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) {
                JTextField self = (JTextField) e.getSource();
                if (!data.isValidUsername(self.getText()))
                    self.setBackground(invalidBackground);
                else
                    self.setBackground(Color.lightGray);
            }
        });
        gbc.gridy = 3;
        container.add(new Label("username"), gbc);
        gbc.gridy = 4;
        container.add(username, gbc);

        JPasswordField password = new JPasswordField(20);
        password.setBorder(new EmptyBorder(10, 10, 10, 10));
        password.setOpaque(true); password.setBackground(Color.lightGray);
        gbc.gridy = 5;
        container.add(new Label("password"), gbc);
        gbc.gridy = 6;
        container.add(password, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] pos = {"manager", "assistant manager", "chef"};
        JComboBox position = new JComboBox(pos);

        if (as == "administrator") {
            gbc.gridy = 7;
            container.add(new Label("choose your position:"), gbc);
            gbc.gridy = 8;
            container.add(position, gbc);
        }

        Button signin = new Button("Sign in");

        signin.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                boolean isValid = true;
                String pssw = new String(password.getPassword());

                email.setBackground(Color.lightGray);
                username.setBackground(Color.lightGray);
                password.setBackground(Color.lightGray);

                if (email.getText().equals("")) {email.setBackground(invalidBackground); isValid = false;}
                if (username.getText().equals("")) {username.setBackground(invalidBackground); isValid = false;}
                if (pssw.equals("")) {password.setBackground(invalidBackground); isValid = false;}

                if (isValid) {
                    if (as.equals("administrator")) {
                        data.add(new AdminAccount(email.getText(), username.getText(), pssw, (String)position.getSelectedItem()));
                    } else {
                        data.add(new CustomerAccount(email.getText(), username.getText(), pssw));
                    }
                    email.setText("");
                    username.setText("");
                    password.setText("");
                }
            }
        });

        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.gridy = 9;
        container.add(signin, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);

        Button back = new Button("<", Color.white, Color.darkGray);
        back.setToolTipText("Go back");
        back.setFont(new Font("Arial", Font.BOLD, 25));

        back.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                root.remove(container);
                root.remove(back);
                root.updateUI();
                customerOrAdmin();
            }
        });

//        container.setOpaque(true);
//        container.setBackground(Color.black);

        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        root.add(back, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        container.setBorder(new EmptyBorder(20, 0, 0, 0));
        root.add(container, gbc);
    }
}
