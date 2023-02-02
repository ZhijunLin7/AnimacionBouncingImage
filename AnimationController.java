import java.util.ArrayList;

public class AnimationController {

    // Atributos
    private AnimationModel animationModel;
    private AnimationView animationView;

    // Constructor
    public AnimationController() {

    }

    public AnimationController(AnimationModel animationModel, AnimationView animationView) {
        this.animationModel = animationModel;
        this.animationView = animationView;
    }

    // Metodo
    public ArrayList<AnimatedObject> getObjects() {
        return animationModel.getAnimatedObjects();
    }

    public int[][] getStatistics() {
        return null;
    }

    public AnimationStatus getAnimationStatus() {
        return animationModel.getAnimationStatus();
    }

    public void pause() {
        this.animationModel.pause();
    }

    public void play() {
        this.animationModel.play();
    }

    public void stop() {
        this.animationModel.stop();
    }

    // Getter y setter
    public AnimationModel getAnimationModel() {
        return animationModel;
    }

    public void setAnimationModel(AnimationModel animationModel) {
        this.animationModel = animationModel;
    }

    public AnimationView getAnimationView() {
        return animationView;
    }

    public void setAnimationView(AnimationView animationView) {
        this.animationView = animationView;
    }
}
