import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class Display extends JPanel{
    public Common common;
    public Dimension getPreferredSize(){
        return new Dimension(800, 400); 
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        common.drawAllEntities(g2d);
    }
    public Display(Common common){
        super();
        this.common = common;
    }
}
