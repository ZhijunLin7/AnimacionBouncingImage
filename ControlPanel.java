import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    // Atributos
    JButton bPlay;
    JButton bPause;
    JButton bStop;
    private int minObjectsALive;
    private int maxObjectsALive;

    // Constructor
    public ControlPanel() {
        bPlay= new JButton("Play");
        this.add(bPlay);
        bPause= new JButton("Pause");
        this.add(bPause);
        bStop= new JButton("Stop");
        this.add(bStop);

        this.setBackground(Color.black);
    }

    public ControlPanel(JButton bPlay, JButton bPause, int minObjectsALive, int maxObjectsALive) {
        this.bPlay = bPlay;
        this.bPause = bPause;
        this.minObjectsALive = minObjectsALive;
        this.maxObjectsALive = maxObjectsALive;
    }

    // Getter y setter
    public JButton getbPlay() {
        return bPlay;
    }

    public void setbPlay(JButton bPlay) {
        this.bPlay = bPlay;
    }

    public JButton getbPause() {
        return bPause;
    }

    public void setbPause(JButton bPause) {
        this.bPause = bPause;
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

    public JButton getbStop() {
        return bStop;
    }

    public void setbStop(JButton bStop) {
        this.bStop = bStop;
    }

}
