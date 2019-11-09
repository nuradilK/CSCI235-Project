import java.util.*;
import javax.swing.*;
import java.awt.*;
public class Student extends Entity{
    public int grade = 0;
    public Student(String name, Vector2D position, Common common){
        this.name = name;
        this.position = position;
        this.common = common;
        this.state = new Rest();
    }
    public void draw(Graphics2D g2d){
        if(grade <= 99){
            g2d.setColor(Color.BLUE);
            g2d.fillOval(position.x, position.y, diameter, diameter);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(position.x, position.y, diameter, diameter);
            g2d.setColor(Color.BLUE);
        } else{
            g2d.setColor(Color.RED);
            g2d.fillOval(position.x, position.y, diameter, diameter);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(position.x, position.y, diameter, diameter);
            g2d.setColor(Color.BLUE);
        }

        Vector2D alligned;
        alligned = allign(g2d, state.getClass().getName(), position, diameter);
        g2d.drawString(state.getClass().getName(), alligned.x, alligned.y);
        alligned = allign(g2d, name, position, diameter);
        g2d.drawString(name, alligned.x, position.y - 3);
        g2d.setColor(Color.BLACK);
        alligned = allign(g2d, new String().valueOf(grade), position, diameter);
        g2d.drawString(new String().valueOf(grade), alligned.x, position.y + diameter / 2 + 2);
    }
}
