import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Label extends JLabel {
    Label(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 12));
        setBorder(new EmptyBorder(30, 0, 5, 0));
        setForeground(Color.darkGray);
    }
}
