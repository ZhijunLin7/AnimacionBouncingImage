import java.util.ArrayList;
import java.util.Random;

public class AnimationModel implements Runnable {

    // Atributos
    private AnimationStatus animationStatus;
    private int minObjectsALive;
    private int maxObjectsALive;
    private ArrayList<AnimatedObject> animatedObjects;
    private AnimationController animationController;

    // Constructor
    public AnimationModel(AnimationStatus animationStatus, int minObjectsALive, int maxObjectsALive) {
        this.animationStatus = animationStatus;
        this.minObjectsALive = minObjectsALive;
        this.maxObjectsALive = maxObjectsALive;
        this.animatedObjects = new ArrayList<>();

    }

    public AnimationModel(AnimationStatus animationStatus, int minObjectsALive, int maxObjectsALive,
            ArrayList<AnimatedObject> animatedObjects) {
        this.animationStatus = animationStatus;
        this.minObjectsALive = minObjectsALive;
        this.maxObjectsALive = maxObjectsALive;
        this.animatedObjects = animatedObjects;
    }

    // Metodos
    public void addNewObject(ObjectTypes objectTypes) {
        AnimatedObject animatedObject = new AnimatedObject(AnimatedObjectStatus.running, objectTypes);
        animatedObjects.add(animatedObject);
    }

    public int[][] getStatistics() {
        return null;
    }

    public synchronized void pause() {

    }

    public synchronized void play() {

    }

    public void setController(AnimationController animationController) {
        this.animationController = animationController;
    }

    private void start() {
        for (AnimatedObject animatedObject : animatedObjects) {
            new Thread(animatedObject).start();

        }
    }

    @Override
    public void run() {

        while (animatedObjects.size() < minObjectsALive) {

            Random random = new Random();

            switch (random.nextInt(4)) {
                case 0:
                    addNewObject(ObjectTypes.zombie);
                    break;
                case 1:
                    addNewObject(ObjectTypes.alien);
                    break;
                case 2:
                    addNewObject(ObjectTypes.soldier);
                    break;
                case 3:
                    addNewObject(ObjectTypes.dog);
                    break;

            }
        }
        start();

    }

    // Getter y setter
    public synchronized AnimationStatus getAnimationStatus() {
        return animationStatus;
    }

    public synchronized void setAnimationStatus(AnimationStatus animationStatus) {
        this.animationStatus = animationStatus;
    }

    public int getMinObjectsALive() {
        return minObjectsALive;
    }

    public void setMinObjectsALive(int minObjectsALive) {
        this.minObjectsALive = minObjectsALive;
    }

    public int getMaxObjectsALive() {
        return maxObjectsALive;
    }

    public void setMaxObjectsALive(int maxObjectsALive) {
        this.maxObjectsALive = maxObjectsALive;
    }

    public ArrayList<AnimatedObject> getAnimatedObjects() {
        return animatedObjects;
    }

    public void setAnimatedObjects(ArrayList<AnimatedObject> animatedObjects) {
        this.animatedObjects = animatedObjects;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }

}
