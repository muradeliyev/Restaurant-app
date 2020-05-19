import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Entry extends JTextField {
    Entry(int cols) {
        super(cols);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setOpaque(true);
        setBackground(Color.lightGray);
    }
}
