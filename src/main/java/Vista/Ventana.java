package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private JButton btnPanelEmpleados = new JButton("Administrar Empleados");
    private JButton btnPanelSuscriptores = new JButton("Administrar Suscriptores");
    private JButton btnPanelClase = new JButton("Administrar Clases");
    private JButton btnVolver = new JButton("Volver");

    private JPanel panelPrincipal = new JPanel();
    private PanelEmpleados panelEmpleados;
    private PanelClientes panelSuscriptores;
    private PanelClase panelClase;

    private ArrayList<JPanel> paneles = new ArrayList<>();

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
        int y = (this.getHeight() / 2) - 100;

        btnPanelEmpleados.setBounds(x, y, 200,30);
        y += 40;
        btnPanelSuscriptores.setBounds(x, y, 200,30);
        y += 40;
        btnPanelClase.setBounds(x,y,200,30);

        btnPanelEmpleados.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelEmpleados = new PanelEmpleados(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelEmpleados.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelEmpleados);
            paneles.add(panelEmpleados);
        });

        btnPanelSuscriptores.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelSuscriptores = new PanelClientes(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelSuscriptores.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelSuscriptores);
            paneles.add(panelSuscriptores);
        });

        btnPanelClase.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelClase = new PanelClase(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelClase.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelClase);
            paneles.add(panelClase);
        });

        btnVolver.addActionListener(e -> {
            paneles.get(1).setVisible(false);
            panelPrincipal.setVisible(true);
        });

        panelPrincipal.setLayout(null);
        panelPrincipal.setSize(this.getSize());
        panelPrincipal.setMaximumSize(this.getSize());
        panelPrincipal.setBackground(Color.BLUE);
        panelPrincipal.add(btnPanelEmpleados);
        panelPrincipal.add(btnPanelSuscriptores);
        panelPrincipal.add(btnPanelClase);
        paneles.add(panelPrincipal);

        this.getContentPane().add(panelPrincipal);
        this.pack();
    }



}
