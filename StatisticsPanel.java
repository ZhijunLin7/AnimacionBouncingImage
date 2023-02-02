import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StatisticsPanel extends JPanel {

    // Atributos
    private int numObjects;
    private JTable jTable;

    // Constructor
    public StatisticsPanel() {

        this.jTable = new JTable(
                new DefaultTableModel(new Object[] { "Column 1", "Column 2", "Column 3", "Column 4", "Column 5" }, 0));

        DefaultTableModel model = (DefaultTableModel) jTable.getModel();

        String[] columnNames = { "", "Running", "Paused", "Stoped", "Dead" };
        String[] zombie = { "zombie", "", "", "", "" };
        String[] alien = { "alien", "", "", "", "" };
        String[] soldier = { "soldier", "", "", "", "" };
        String[] dog = { "dog", "", "", "", "" };

        model.addRow(columnNames);
        model.addRow(zombie);
        model.addRow(alien);
        model.addRow(soldier);
        model.addRow(dog);

        this.setBackground(Color.CYAN);
        this.add(jTable);
        

    }

    public StatisticsPanel(int numObjects, JTable jTable) {
        this.numObjects = numObjects;
        this.jTable = jTable;
    }

    // Metodos
    public void setStatistics() {

    }

    // Getter y setter
    public int getNumObjects() {
        return numObjects;
    }

    public void setNumObjects(int numObjects) {
        this.numObjects = numObjects;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

}
