import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static java.awt.Color.darkGray;
import static java.awt.Color.white;

public class Button extends JButton {
    Button(String text) {
        this(text, darkGray, white);
    }
    Button(String text, Color bg, Color fg) {
        this(text, bg, fg, new EmptyBorder(10, 10, 10, 10));
    }
    Button(String text, Color bg, Color fg, EmptyBorder border) {
        super(text);
        setOpaque(true);
        setBackground(bg);
        setForeground(fg);
        setFocusPainted(false);
        setRolloverEnabled(false);
        setBorder(border);
    }
}
