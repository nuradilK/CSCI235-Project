import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NURunner{ 
    public static JFrame window;
    public static Display display;
    public static Common common ;

    public static void main(String args[]){
        common = new Common();
        window = new JFrame();
        display = new Display(common);
        Container cp = window.getContentPane();
        List<String> studentNames = List.of("Nuradil", "Alimkhan", "Aslan", "Khafiz", "Rauan", "Salavat"); 
        List<String> academicianNames = List.of("HansDeNivelle", "SelimTemizer", "ShigeoKatsu", "VassiliosTourassis");
        List<String> speakerNames = List.of("KassymJomartTokayev", "NursultanNazarbayev");

        for(String i: studentNames){
            int randomX = common.randomInt(0, 800), randomY = common.randomInt(0, 400);
            common.students.add(new Student(i, new Vector2D(randomX, randomY), common));
        }
        for(String i: academicianNames){
            int randomX = common.randomInt(0, 800), randomY = common.randomInt(0, 400);
            common.academicians.add(new Academician(i, new Vector2D(randomX, randomY), common));
        }
        for(String i: speakerNames){
            int randomX = common.randomInt(0, 800), randomY = common.randomInt(0, 400);
            common.speakers.add(new Speaker(i, new Vector2D(randomX, randomY), common));
        }

        display.repaint();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp.add(display);
        window.pack();
        window.setVisible(true);

        while(true){
            common.stepAllEntities();
            display.repaint();
            try{
                Thread.sleep(5);
            } catch(InterruptedException e){
                System.out.println(e);
            }  
        }
    }
}
