package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelSuscriptores extends JPanel {

    private JLabel lbCI = new JLabel("CI: ");
    private JLabel lbNombre = new JLabel("Nombre: ");
    private JLabel lbApellidos = new JLabel("Apellidos: ");
    private JLabel lbTelefono = new JLabel("Telefono: ");

    public PanelSuscriptores(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);
    }

}
