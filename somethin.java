import java.util.*;
import javax.swing.*;
import java.awt.*;

public class somethin{
    static public JFrame window;
    static public Display display;
    public static void main(String args[]){
        window = new JFrame();
        display = new Display();
        Container cp = window.getContentPane();
        display.repaint(100, 500, 50, 50);

        window.setPreferredSize(display.getPreferredSize());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.RED);
        cp.add(display);
        window.pack();
        window.setVisible(true);
    }
}
