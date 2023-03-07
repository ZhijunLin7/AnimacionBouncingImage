import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;


import java.awt.Canvas;
import java.awt.Graphics;

public class Viewer extends Canvas {

    // Atributos
    private Image background;


    // Constructor
    public Viewer() {
        try {
            background=ImageIO.read(new File("imagen//bagroundImage.jpg"));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public Viewer(Image background) {
        this.background = background;
    }

    // Metodo
    public void drawBackgroud(Graphics g) {
        if (g==null) {
            System.out.println("No existe manejador de imagen");
        }
        g.drawImage(this.background,0,0,getWidth(),getHeight(),null);
        
    }



    // Getter y setter
    public void setBackground(Image background) {
        this.background = background;
    }
}
