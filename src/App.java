import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

import static java.awt.Color.*;

public class App extends JFrame {
    GridBagConstraints gbc;

    App() {
        super("Restaurant");
        setLayout(new GridBagLayout());
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

        Button button;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        button = new Button("1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        main.add(button, gbc);

        button = new Button("2");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(-30, -30, 0, 0);
        main.add(button, gbc);

        main.setOpaque(true);
        main.setBackground(Color.red);

        gbc.fill = GridBagConstraints.BOTH;
        add(main, gbc);
    }
}
