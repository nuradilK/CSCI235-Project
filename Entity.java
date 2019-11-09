import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.List;

public abstract class Entity{
    public String name;
    public Vector2D position;
    public State state;
    public Common common;
    protected final int diameter = 40, photoWidth = 40, photoHeight = 60, duration = 10;

    public abstract void draw(Graphics2D g2d);
    public void step(){
        state.step(this);
    }
    protected Image readImg(String imgPath){
        Image img = null;
        try{
            img = ImageIO.read(new File(imgPath));
        } catch(IOException e){
            System.out.println(e);
        }
        return img;
    }
    protected Image resizeImg(Image img, int newW, int newH) {  
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    } 
    protected Vector2D allign(Graphics2D g2d, String text, Vector2D position, int diameter){
        int width, height, allignedX, allignedY;
        Font font;
        FontRenderContext context;

        font = g2d.getFont();
        context = g2d.getFontRenderContext();
        width = (int)(font.getStringBounds(text, context).getWidth());
        height = (int)(font.getStringBounds(text, context).getHeight());
        allignedX = position.x + diameter / 2 - width / 2;
        allignedY = position.y + diameter + height;
        return new Vector2D(allignedX, allignedY);
    }
}
