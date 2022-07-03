package Vista;

import javax.swing.*;

public class VentanaEmergente extends JFrame {

    private JPanel panel;

    public VentanaEmergente(JPanel panel){
        this.panel = panel;
        setSize(panel.getSize());
        init1();
    }

    private void init1() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.add(panel);
    }


}
