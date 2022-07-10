package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private JButton btnPanelEmpleados = new JButton("Administrar Empleados");
    private JButton btnPanelSuscriptores = new JButton("Administrar Suscriptores");
    private JButton btnPanelClase = new JButton("Administrar Clases");
    private JButton btnPanelZonas = new JButton("Administrar Zonas");
    private JButton btnPanelSuscripciones = new JButton("Administrar Suscripciones");
    private JButton btnPanelAccesos = new JButton("Administrar Accesos");
    private JButton btnPanelIncidentes = new JButton("Administrar Incidentes");
    private JButton btnPanelCitas = new JButton("Administrar Citas");
    private JButton btnVolver = new JButton("Volver");

    private JPanel panelPrincipal = new JPanel();
    private PanelEmpleados panelEmpleados;
    private PanelSuscriptores panelSuscriptores;
    private PanelClase panelClase;
    private PanelZonas panelZonas;
    private PanelSuscripciones panelSuscripciones;
    private PanelAccesos panelAccesos;
    private PanelInicidentes panelInicidentes;
    private PanelCitas panelCitas;

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
        int y = 120;

        btnPanelEmpleados.setBounds(x, y, 200,30);
        y += 40;
        btnPanelSuscriptores.setBounds(x, y, 200,30);
        y += 40;
        btnPanelClase.setBounds(x,y,200,30);
        y += 40;
        btnPanelZonas.setBounds(x,y,200,30);
        y += 40;
        btnPanelSuscripciones.setBounds(x,y,200,30);
        y += 40;
        btnPanelAccesos.setBounds(x,y,200,30);
        y += 40;
        btnPanelIncidentes.setBounds(x,y,200,30);
        y += 40;
        btnPanelCitas.setBounds(x,y,200,30);

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

            panelSuscriptores = new PanelSuscriptores(this.getSize());
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

        btnPanelZonas.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelZonas = new PanelZonas(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelZonas.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelZonas);
            paneles.add(panelZonas);
        });

        btnPanelSuscripciones.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelSuscripciones = new PanelSuscripciones(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelSuscripciones.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelSuscripciones);
            paneles.add(panelSuscripciones);
        });

        btnPanelAccesos.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelAccesos = new PanelAccesos(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelAccesos.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelAccesos);
            paneles.add(panelAccesos);
        });

        btnPanelIncidentes.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelInicidentes = new PanelInicidentes(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelInicidentes.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelInicidentes);
            paneles.add(panelInicidentes);
        });

        btnPanelCitas.addActionListener(e -> {
            if (paneles.size() == 2){
                paneles.remove(1);
            }

            panelCitas = new PanelCitas(this.getSize());
            btnVolver.setBounds(0,0,75,30);
            panelCitas.add(btnVolver);
            panelPrincipal.setVisible(false);
            this.add(panelCitas);
            paneles.add(panelCitas);
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
        panelPrincipal.add(btnPanelZonas);
        panelPrincipal.add(btnPanelSuscripciones);
        panelPrincipal.add(btnPanelAccesos);
        panelPrincipal.add(btnPanelIncidentes);
        panelPrincipal.add(btnPanelCitas);
        paneles.add(panelPrincipal);

        this.getContentPane().add(panelPrincipal);
        this.pack();
    }



}
