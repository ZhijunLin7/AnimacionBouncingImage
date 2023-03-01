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

    // Metodos
    public void addNewObject(ObjectTypes objectTypes) {
        AnimatedObject animatedObject = new AnimatedObject(AnimatedObjectStatus.running, objectTypes, this);
        animatedObjects.add(animatedObject);
        this.start(animatedObject);
    }

    public int[][] getStatistics() {
        return null;
    }

    public synchronized void pause() {
        if (this.animationStatus.equals(AnimationStatus.running)) {
            this.animationStatus = AnimationStatus.paused;
            for (AnimatedObject animatedObject : animatedObjects) {
                if (animatedObject.getAnimatedObjectStatus()!= AnimatedObjectStatus.dead) {
                    animatedObject.setAnimatedObjectStatus(AnimatedObjectStatus.paused);
                }
            }
        }
    }

    public synchronized void stop() {
        notifyAll();
        this.animationStatus = AnimationStatus.stopped;
        for (int i = 0; i < animatedObjects.size(); i++) {
            if (animatedObjects.get(i).getAnimatedObjectStatus()!= AnimatedObjectStatus.stopped) {
                this.animatedObjects.get(i).setAnimatedObjectStatus(AnimatedObjectStatus.stopped);
            }
        }
    }

    public synchronized void play() {
        if (this.animationStatus.equals(AnimationStatus.paused)) {
            this.animationStatus = AnimationStatus.running;
            for (AnimatedObject animatedObject : animatedObjects) {
                if (animatedObject.getAnimatedObjectStatus()!= AnimatedObjectStatus.dead) {
                    animatedObject.setAnimatedObjectStatus(AnimatedObjectStatus.running);
                }
            }
            notifyAll();
        } else if (this.animationStatus.equals(AnimationStatus.stopped)) {
            this.animationStatus = AnimationStatus.running;
            this.animatedObjects = new ArrayList<>();
            Thread model = new Thread(this);
            model.start();
            Thread viewr = new Thread(this.animationController.getAnimationView());
            viewr.start();
        }
    }

    private void start(AnimatedObject animatedObject) {
        Thread t = new Thread(animatedObject);
        t.start();
    }

    public void setController(AnimationController animationController) {
        this.animationController = animationController;
    }

    @Override
    public void run() {
        while (true) {
            int numalive=0;
            for (AnimatedObject animatedObject : animatedObjects) {
                if (animatedObject.getAnimatedObjectStatus()!=AnimatedObjectStatus.dead) {
                    numalive+=1;
                }
            }
            if (numalive<getMaxObjectsALive()) {
                Random random = new Random();
                System.out.println("generado");
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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // Getter y setter
    public synchronized AnimationStatus getAnimationStatus() {
        if (this.animationStatus.equals(AnimationStatus.paused)) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
