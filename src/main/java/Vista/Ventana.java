package Vista;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private JButton btnPanelEmpleados = new JButton("Administrar Empleados");
    private JButton btnPanelSuscriptores = new JButton("Administrar Empleados");

    private JPanel panelPrincipal = new JPanel();
    private PanelEmpleados panelEmpleados;
    private PanelClientes panelClientes;

    public Ventana(){
        init1();
    }

    public void init1(){
        this.setLayout(null);
        this.setSize(1000,600);
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        int x = this.getWidth()/2 - 100;
        int y;

        btnPanelEmpleados.setBounds(x, (this.getHeight() / 2) - 100, 200,30);
        y = btnPanelEmpleados.getY();
        btnPanelSuscriptores.setBounds(x, y + 100, 200,30);

        btnPanelEmpleados.addActionListener(e -> {
            this.remove(panelPrincipal);
            panelEmpleados = new PanelEmpleados(this.getSize());
            this.add(panelEmpleados);
        });

        panelPrincipal.setLayout(null);
        panelPrincipal.setSize(this.getSize());
        panelPrincipal.setMaximumSize(this.getSize());
        panelPrincipal.setBackground(Color.BLUE);
        panelPrincipal.add(btnPanelEmpleados);
        panelPrincipal.add(btnPanelSuscriptores);

        this.getContentPane().add(panelPrincipal);
        this.pack();
    }

}
