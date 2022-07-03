package Vista;

import DAO.HorarioDAO;
import Modelo.Empleado;
import Modelo.Horario;
import PlaceHolders.TextPrompt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelInstructores extends JPanel {

    private JButton btnVerHorarios = new JButton("Ver Horarios");
    private JButton btnCrearHorario = new JButton("Definir Nuevo Horario");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private JPanel panelSeleccionar = new JPanel();
    private JPanel panelRegistrar = new JPanel();
    private JPanel panelHorarios = new JPanel();

    private ArrayList<JPanel> paneles = new ArrayList<>();
    private Empleado empleado;
    private HorarioDAO horarioDAO = new HorarioDAO();

    private boolean idAutomatico = true;

    JTextField txtID = new JTextField();
    JComboBox<String> cbDia = new JComboBox<>();
    JTextField txtHoraInicio = new JTextField();
    JTextField txtHoraFinal = new JTextField();

    public PanelInstructores(Empleado empleado){
        setSize(600,600);
        this.empleado = empleado;
        init1();
    }

    private void init1() {
        setLayout(null);
        int y = this.getHeight() / 4;
        this.panelSeleccionar.setBounds(0,0,this.getWidth(), y);
        this.panelRegistrar.setBounds(0,0,this.getWidth(), y);
        this.panelHorarios.setBounds(0,y,this.getWidth(),(y*3) - 30);

        paneles.add(panelHorarios);
        paneles.add(panelSeleccionar);

        cargarPanelSeleccionar();
        cargarPanelTabla();
        cargarPanelRegistrar();

        this.add(panelSeleccionar);
        this.add(panelRegistrar);
        panelRegistrar.setVisible(false);
        this.add(panelHorarios);
    }

    private void cargarPanelSeleccionar(){
        panelSeleccionar.setLayout(null);
        panelSeleccionar.setBackground(Color.BLUE);

        int x = panelSeleccionar.getWidth() / 2 - 200;
        int y = panelSeleccionar.getHeight() / 2 - 15;
        btnVerHorarios.setBounds(x,y,150,30);
        x += 200;
        btnCrearHorario.setBounds(x,y,150,30);
        btnCrearHorario.setMargin(new Insets(0,0,0,0));

        btnCrearHorario.addActionListener(e -> {
            panelSeleccionar.setVisible(false);
            panelRegistrar.setVisible(true);
        });

        panelSeleccionar.add(btnCrearHorario);
        panelSeleccionar.add(btnVerHorarios);
    }

    private void cargarPanelRegistrar(){
        panelRegistrar.setLayout(null);
        panelRegistrar.setBackground(Color.RED);

        JLabel lbID = new JLabel("ID: ");
        JLabel lbDia = new JLabel("Día: ");
        JLabel lbHoraInicio = new JLabel("Hora de Inicio: ");
        JLabel lbHoraFinal = new JLabel("Hora de Final: ");

        JButton btnAgregar = new JButton("Agregar");
        JButton btnAtras = new JButton("Atras");

        btnAtras.setBounds(0,0,50,30);
        btnAtras.setMargin(new Insets(0,0,0,0));

        int x = panelRegistrar.getWidth() / 2 - 270 -15;
        int y = 20;

        lbID.setBounds(x,y,100,30);
        lbID.setHorizontalAlignment(JLabel.RIGHT);
        x+=100;
        txtID.setBounds(x,y,150,30);
        TextPrompt IDph = new TextPrompt("No tocar para un ID automatico", txtID);
        y+=40;
        x-=100;
        lbDia.setBounds(x,y,100,30);
        lbDia.setHorizontalAlignment(JLabel.RIGHT);
        x+=100;
        cbDia.setBounds(x,y,150,30);
        y = 20;
        x += 150;
        lbHoraInicio.setBounds(x,y,100,30);
        lbHoraInicio.setHorizontalAlignment(JLabel.RIGHT);
        x+=100;
        txtHoraInicio.setBounds(x,y,150,30);
        TextPrompt HoraInicioph = new TextPrompt("HH:MM:SS", txtHoraInicio);
        y+=40;
        x-=100;
        lbHoraFinal.setBounds(x,y,100,30);
        lbHoraFinal.setHorizontalAlignment(JLabel.RIGHT);
        x+=100;
        txtHoraFinal.setBounds(x,y,150,30);
        TextPrompt HoraFinalph = new TextPrompt("HH:MM:SS", txtHoraFinal);
        y+=40;

        btnAgregar.setBounds(240,y,100,30);

        btnAgregar.addActionListener(e -> {
            String aux = txtID.getText().trim();
            Integer id = null;

            if (!aux.isEmpty()){
                try {
                    id = Integer.parseInt(aux);
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "El ID solo acepta numeros!");
                    return;
                }
            }

            String dia = (String) cbDia.getSelectedItem();
            String HoraInicio = txtHoraInicio.getText();
            String HoraFinal = txtHoraFinal.getText();
            Horario horario = new Horario(id,dia,HoraInicio,HoraFinal, empleado.CI());
            horarioDAO.registrarHorario(horario);
            limpiarDatos();
            leerDatos();
            JOptionPane.showMessageDialog(null, "Se ha definido el horario exitosamente!");
        });

        btnAtras.addActionListener(e -> {
            panelRegistrar.setVisible(false);
            panelSeleccionar.setVisible(true);
        });

        cargarDias();

        panelRegistrar.add(lbDia);
        panelRegistrar.add(lbID);
        panelRegistrar.add(lbHoraInicio);
        panelRegistrar.add(lbHoraFinal);
        panelRegistrar.add(txtID);
        panelRegistrar.add(cbDia);
        panelRegistrar.add(txtHoraInicio);
        panelRegistrar.add(txtHoraFinal);
        panelRegistrar.add(btnAgregar);
        panelRegistrar.add(btnAtras);
    }

    private void cargarPanelTabla(){
        panelHorarios.setLayout(null);

        for (String columna: horarioDAO.columnas()) {
            defaultTableModel.addColumn(columna);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);

        scrollPane.setBounds(0,0, panelHorarios.getWidth()-15,panelHorarios.getHeight());

        panelHorarios.add(scrollPane);
    }

    private void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Horario horario: horarioDAO.buscarHorarioEmpleado(empleado.CI())) {
            defaultTableModel.addRow(horario.getDatos());
        }
    }

    public void cargarDias(){
        cbDia.addItem("Lunes");
        cbDia.addItem("Martes");
        cbDia.addItem("Miercoles");
        cbDia.addItem("Jueves");
        cbDia.addItem("Viernes");
    }

    private void limpiarDatos(){
        txtID.setText("");
        txtHoraInicio.setText("");
        txtHoraFinal.setText("");
    }

}
