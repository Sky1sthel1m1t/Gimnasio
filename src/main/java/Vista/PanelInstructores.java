package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelInstructores extends JPanel {

    private JButton btnVerClases = new JButton("Ver Clases");
    private JButton btnVerHorarios = new JButton("Ver Horarios");

    private JPanel panelClases;
    private JPanel panelHorarios;

    private ArrayList<JPanel> paneles = new ArrayList<>();

    public PanelInstructores(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);
        int x = 0;
        int y = 0;

    }

}
