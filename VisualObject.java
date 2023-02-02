import java.awt.Graphics;

import javax.swing.text.Position;

/**
 * VisualObject
 */
public class VisualObject {

    // Atributos
    private Coordinates position;

    // Constructor
    public VisualObject() {
        position = new Coordinates();
    }

    public VisualObject(Coordinates position) {
        this.position = position;
    }

    // Metodos
    public synchronized void drawObject(Graphics g) {

    }

    // Getter y setter
    public synchronized Coordinates getPosition() {
        return position;
    }

    private synchronized void setPosition(Coordinates position) {
        this.position = position;
    }
}