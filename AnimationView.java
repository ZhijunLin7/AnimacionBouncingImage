import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimationView extends JFrame implements Runnable, ActionListener {
    // Atributos
    public int refreshMillis;
    private StatisticsPanel statisticsPanel;
    private ControlPanel controlPanel;
    private Viewer viewer;
    private AnimationController animationController;

    // Constructor
    public AnimationView(int refreshMillis) {
        this.refreshMillis = refreshMillis;
        this.statisticsPanel = new StatisticsPanel();
        this.controlPanel = new ControlPanel();
        this.viewer = new Viewer();

        // Configurar el layout
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        GridBagConstraints c;

        // Posicionamiento de StatisticaPanel
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.ipadx = 300;
        this.add(statisticsPanel, c);

        // Posicionamiento de ControlaPnel
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        this.add(controlPanel, c);

        // añadir actionlistener al boton
        controlPanel.getbPause().addActionListener(this);
        controlPanel.getbPlay().addActionListener(this);
        controlPanel.getbStop().addActionListener(this);

        // Posicionamiento de Baground de viewer
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        this.add(viewer, c);

        this.setDefaultCloseOperation(3);
        this.setSize(1200, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Animation");
        this.setVisible(true);

        viewer.paint(getGraphics());

    }

    public AnimationView(int refreshMillis, StatisticsPanel statisticsPanel, ControlPanel controlPanel, Viewer viewer) {
        this.refreshMillis = refreshMillis;
        this.statisticsPanel = statisticsPanel;
        this.controlPanel = controlPanel;
        this.viewer = viewer;
    }

    // Metodo
    public void refresh(ArrayList<AnimatedObject> animatedObjects) {
        ArrayList<AnimatedObject> runningObjects = new ArrayList<>();
        ArrayList<AnimatedObject> deadObjects = new ArrayList<>();

        for (AnimatedObject animatedObject : animatedObjects) {
            if (animatedObject.getAnimatedObjectStatus().equals(AnimatedObjectStatus.running)) {
                animatedObject.setCanvaHeight(viewer.getHeight());
                animatedObject.setCanvaWith(viewer.getWidth());
                runningObjects.add(animatedObject);
            }else if (animatedObject.getAnimatedObjectStatus().equals(AnimatedObjectStatus.dead)) {
                deadObjects.add(animatedObject);
            }
        }
        for (int i = 0; i < runningObjects.size(); i++) {
            runningObjects.get(i).drawObject(viewer.getGraphics());
        }
        for (int i = 0; i < deadObjects.size(); i++) {
            deadObjects.remove(i);
        }
    }

    public int[][] getStatistics() {
        return null;
    }

    public void setController(AnimationController animationController) {
        this.animationController = animationController;
    }

    @Override
    public void run() {
        while (this.animationController.getAnimationModel().getAnimationStatus() != AnimationStatus.stopped ) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            viewer.drawBackgroud();
            refresh(animationController.getObjects());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String butname = e.getActionCommand();
        switch (butname) {
            case "Play":
                this.animationController.play();
                break;
            case "Pause":
            this.animationController.pause();
                break;
            case "Stop":
            this.animationController.stop();
                break;
        }
    }

    // Getter y setter
    public int getRefreshMillis() {
        return refreshMillis;
    }

    public void setRefreshMillis(int refreshMillis) {
        this.refreshMillis = refreshMillis;
    }

    public StatisticsPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    public void setStatisticsPanel(StatisticsPanel statisticsPanel) {
        this.statisticsPanel = statisticsPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

}
