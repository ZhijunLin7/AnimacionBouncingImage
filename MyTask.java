public class MyTask {

    // Atributos
    private AnimationController animationController;
    private AnimationModel animationModel;
    private AnimationView animationView;

    // Constructor
    public MyTask() {
        AnimatedObject.loadObjectImages();
        this.animationModel = new AnimationModel(AnimationStatus.running, 4, 6);
        new Thread(animationModel).start();

        this.animationView = new AnimationView(500);
        this.animationController = new AnimationController(animationModel, animationView);

    }

    public MyTask(AnimationController animationController, AnimationModel animationModel, AnimationView animationView) {
        this.animationController = animationController;
        this.animationModel = animationModel;
        this.animationView = animationView;
    }

    // Getter y setter
    public AnimationController getAnimationController() {
        return animationController;
    }

    public void setAnimationController(AnimationController animationController) {
        this.animationController = animationController;
    }

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

    public static void main(String[] args) {
        AnimatedObject.loadObjectImages();

        AnimationModel animationModel = new AnimationModel(AnimationStatus.stopped, 4, 6);
        AnimationView animationView = new AnimationView(500);
        AnimationController animationController = new AnimationController(animationModel, animationView);

        animationView.setController(animationController);
        animationModel.setController(animationController);


    }
}
