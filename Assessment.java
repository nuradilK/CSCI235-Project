import java.util.*;
import javax.swing.*;
import java.awt.*;
public abstract class Assessment extends Entity{
    int points;
    public boolean inRange(Vector2D target, Vector2D object){
        return (target.distanceTo(object) <= 15.0);
    }
    public Assessment(Vector2D position, int points, Common common){
        this.points = points;
        this.position = position;
        this.state = new Stationary();
        this.common = common;
        this.name = String.valueOf(points);
    }
}

class Lab extends Assessment{
    public Lab(Vector2D position, int points, Common common){
        super(position, points, common);
    }
    public void draw(Graphics2D g2d){
        if(state.isVisible){
            g2d.setColor(Color.RED);
            g2d.fillOval(position.x, position.y, 20, 20);
            g2d.setColor(Color.BLACK);

            Vector2D alligned;
            alligned = allign(g2d, new String().valueOf(points), position, 20);
            g2d.drawString(new String().valueOf(points), alligned.x, position.y + diameter / 2 - 5);
        }
    }
}
class Quiz extends Assessment{
    public Quiz(Vector2D position, int points, Common common){
        super(position, points, common);
    }
    public void draw(Graphics2D g2d){
        if(state.isVisible){
            g2d.setColor(Color.GREEN);
            g2d.fillRect(position.x, position.y, 20, 20);
            g2d.setColor(Color.BLACK);

            Vector2D alligned;
            alligned = allign(g2d, new String().valueOf(points), position, 20);
            g2d.drawString(new String().valueOf(points), alligned.x, position.y + diameter / 2 - 5);
        }
    }
}
class Homework extends Assessment{
    public Homework(Vector2D position, int points, Common common){
        super(position, points, common);
    }
    public void draw(Graphics2D g2d){
        if(state.isVisible){
            int x[] = {10, 0, 20}, y[] = {0, 20, 20};
            for(int i=0; i<3; ++i){
                x[i] += position.x;
                y[i] += position.y;
            }
            g2d.setColor(Color.BLUE);
            g2d.fillPolygon(x, y, 3);
            g2d.setColor(Color.BLACK);

            Vector2D alligned;
            alligned = allign(g2d, new String().valueOf(points), position, 20);
            g2d.drawString(new String().valueOf(points), alligned.x, position.y + diameter / 2 - 3);
        }
    }
}
