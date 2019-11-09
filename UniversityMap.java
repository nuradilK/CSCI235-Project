import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
public class UniversityMap extends Entity{
    private String imgPath = "Demo/NUMap-Faded.jpg";
    public UniversityMap(){
        this.name = "map";
        this.position = new Vector2D(0, 0);
        this.state = new Stationary();
    }
    public UniversityMap(Common common){
        this.name = "map";
        this.position = new Vector2D(0, 0);
        this.state = new Stationary();
        this.common = common;
    }
    public void step(){
    }
    public void draw(Graphics2D g2d){
        Image img = readImg(imgPath);
        g2d.drawImage(img, 0, 0, null);
    }
}
