import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import java.awt.Graphics;

/**
 * AnimatedObject
 */
public class AnimatedObject extends VisualObject implements Runnable {

    // Atributos
    private static Image[] objectImages;
    private static int[][] statistics;
    private AnimatedObjectStatus animatedObjectStatus;
    private ObjectTypes objectTypes;
    private int canvaHeight;
    private int canvaWith;
    private AnimationModel animationModel;
    boolean move_up, move_left;

    // Constructor
    public AnimatedObject(AnimatedObjectStatus animatedObjectStatus, ObjectTypes objectTypes,
            AnimationModel animationModel) {
        this.animatedObjectStatus = animatedObjectStatus;
        this.objectTypes = objectTypes;
        this.animationModel = animationModel;
    }

    // Metodos
    public static void loadObjectImages() {
        objectImages = new Image[4];
        try {
            objectImages[0] = ImageIO.read(new File("imagen//zombie.png"));
            objectImages[1] = ImageIO.read(new File("imagen//soldier.png"));
            objectImages[2] = ImageIO.read(new File("imagen//dog.png"));
            objectImages[3] = ImageIO.read(new File("imagen//alien.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void addToStatistics() {
        int[][] estadistic = new int[4][4];
        for (AnimatedObject object : this.animationModel.getAnimatedObjects()) {
            switch (object.objectTypes) {
                case zombie:
                    this.estado(estadistic[0],object);
                    break;
                case alien:
                    this.estado(estadistic[1],object);
                    break;
                case soldier:
                    this.estado(estadistic[2],object);
                    break;
                case dog:
                    this.estado(estadistic[3],object);
                    break;
            }
        }
        AnimatedObject.statistics = estadistic;
    }

    @Override
    public synchronized void drawObject(Graphics g) {
        ObjectTypes objectTypes = this.getObjectTypes();
        Image image = null;
        switch (objectTypes) {
            case zombie:
                image = AnimatedObject.getObjectImages()[0];
                break;
            case soldier:
                image = AnimatedObject.getObjectImages()[1];
                break;
            case dog:
                image = AnimatedObject.getObjectImages()[2];
                break;
            case alien:
                image = AnimatedObject.getObjectImages()[3];
                break;
        }
        g.drawImage(image, super.getPosition().getX(), super.getPosition().getY(), 80, 110, null);
    }

    public synchronized void removeFromStatistics(AnimationStatus animationStatus) {

    }

    public synchronized void updateStatistics() {

    }

    public void estado(int[] estadistic,AnimatedObject object) {
        switch (object.animatedObjectStatus) {
            case running:
                estadistic[0] += 1;
                break;
            case paused:
                estadistic[1] += 1;
                break;
            case stopped:
                estadistic[2] += 1;
                break;
            case dead:
                estadistic[3] += 1;
                break;
        }
    }

    @Override
    public void run() {

        Random random = new Random();
        super.getPosition().setX(random.nextInt(800));
        super.getPosition().setY(random.nextInt(800));

        while (this.animationModel.getAnimationStatus() != AnimationStatus.stopped
                && this.animatedObjectStatus != AnimatedObjectStatus.dead) {
            this.mover();
            this.addToStatistics();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void mover() {
        Random random = new Random();
        if ((random.nextInt(1000)<1)) {
            this.animatedObjectStatus=AnimatedObjectStatus.dead;
        }
        int y = super.getPosition().getY();
        int x = super.getPosition().getX();
        if (x > getCanvaWith() - 80) {
            move_left = true;
        }
        if (x < 0) {
            move_left = false;
        }
        if (move_left) {
            x -= 1;
        } else {
            x += 1;
        }
        if (y > getCanvaHeight() - 110) {
            move_up = true;
        }
        if (y < 0) {
            move_up = false;
        }
        if (move_up) {
            y -= 1;
        } else {
            y += 1;
        }
        super.getPosition().setX(x);
        super.getPosition().setY(y);
    }

    // Getter y setter
    public static Image[] getObjectImages() {
        return objectImages;
    }

    public static void setObjectImages(Image[] objectImages) {
        AnimatedObject.objectImages = objectImages;
    }

    public static int[][] getStatistics() {
        return statistics;
    }

    public static void setStatistics(int[][] statistics) {
        AnimatedObject.statistics = statistics;
    }

    public synchronized AnimatedObjectStatus getAnimatedObjectStatus() {
        return animatedObjectStatus;
    }

    public int getCanvaHeight() {
        return canvaHeight;
    }

    public void setCanvaHeight(int canvaHeight) {
        this.canvaHeight = canvaHeight;
    }

    public int getCanvaWith() {
        return canvaWith;
    }

    public void setCanvaWith(int canvaWith) {
        this.canvaWith = canvaWith;
    }

    public synchronized void setAnimatedObjectStatus(AnimatedObjectStatus animatedObjectStatus) {
        this.animatedObjectStatus = animatedObjectStatus;
    }

    public ObjectTypes getObjectTypes() {
        return objectTypes;
    }

    public void setObjectTypes(ObjectTypes objectTypes) {
        this.objectTypes = objectTypes;
    }

    public AnimationModel getAnimationModel() {
        return animationModel;
    }

    public void setAnimationModel(AnimationModel animationModel) {
        this.animationModel = animationModel;
    }

}