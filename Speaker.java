import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Speaker extends Entity{
    public Speaker(String name, Vector2D position, Common common){
        this.name = name;
        this.position = position;
        this.common = common;
        this.state = new Invisible();
    }
    public void draw(Graphics2D g2d){
        if(!state.isVisible)
            return;
        Vector2D alligned, alligned2;
        Image img = readImg("Demo/" + name + ".gif");
        img = resizeImg(img, photoWidth, photoHeight);
        g2d.drawImage(img, position.x, position.y, null);
 
        g2d.setColor(Color.BLUE);
        alligned = allign(g2d, state.getClass().getName(), position, photoWidth);
        alligned2 = allign(g2d, state.getClass().getName(), position, photoHeight);
        g2d.drawString(state.getClass().getName(), alligned.x, alligned2.y);
        alligned = allign(g2d, name, position, photoWidth);
        g2d.drawString(name, alligned.x, position.y - 3);    
    }
}
