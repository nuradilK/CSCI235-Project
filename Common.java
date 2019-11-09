import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Common{
    public int windowWidth = 800, windowHeight = 400;
    public UniversityMap map = new UniversityMap(this);
    public ArrayList<Academician> academicians = new ArrayList<>();
    public ArrayList<Speaker> speakers = new ArrayList<>();
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Assessment> assessments = new ArrayList<>();
    private Random randomGenerator = new Random();
    private int add = 0;
    private boolean done = false;

    public Common(){
        
    }
    public int randomInt(int from, int to){
        int range = to - from + 1                   ;
        return from + randomGenerator.nextInt(range);
    }
    public void stepAllEntities(){
        add = 0;
        for(Entity i: academicians){
            if(done == true){
                if(i.position.equals(new Vector2D(440 + add, 220)))
                    i.state = new Stationary();
                else
                    i.state = new GotoXY(new Vector2D(440 + add, 220));
                add += 70;
            }
            i.step();
        }
        done = true;
        for(Student i: students){
            i.step();
            if(i.grade >= 100){
                String name = i.state.getClass().getName();
                GotoXY gotoXY = null;
                if(name != "Stationary" && name != "GotoXY"){
                    i.state = new GotoXY(new Vector2D(540 + randomInt(0, 15), 315 + randomInt(0, 15)));
                } else if(name == "GotoXY"){
                    gotoXY = (GotoXY) i.state;
                    if(gotoXY.location.distanceTo(new Vector2D(540, 315)) > Math.sqrt(450)){
                        i.state = new GotoXY(new Vector2D(540 + randomInt(0, 10), 315 + randomInt(0, 10)));
                    }
                }
                name = i.state.getClass().getName();
                if(name == "GotoXY")
                    gotoXY = (GotoXY) i.state;
                if(name != "Stationary" && i.position.equals(gotoXY.getLocation())){
                    i.state = new Stationary();
                } else if(name != "Stationary"){
                    done = false;
                }
            } else{
                done = false;
            }
        }
        for(Entity i: assessments){
            if(done == true)
                i.state = new Invisible();
            i.step();
        }
        add = 0;
        for(Entity i: speakers){
            if(done == true){
                i.state = new Stationary();
                i.position = new Vector2D(500 + add, 310);
                add += 80;
            }
            i.step();
        } 
    }
    public void drawAllEntities(Graphics2D g2d){
        map.draw(g2d);
        for(int i=0; i<assessments.size(); ++i){
            assessments.get(i).draw(g2d);
        }
        for(int i=0; i<students.size(); ++i){
            students.get(i).draw(g2d);
        }
        for(int i=0; i<speakers.size(); ++i){
            speakers.get(i).draw(g2d);
        }
        for(int i=0; i<academicians.size(); ++i){
            academicians.get(i).draw(g2d);
        }
    }
}
