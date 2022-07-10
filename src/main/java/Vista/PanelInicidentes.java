package Vista;

import DAO.EmpleadoDAO;
import DAO.IncidenteDAO;
import DAO.SuscriptorDAO;
import Modelo.Empleado;
import Modelo.Incidente;
import Modelo.Suscriptor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class PanelInicidentes extends JPanel {

    private JLabel lbFecha = new JLabel("Fecha: ");
    private JLabel lbMedico = new JLabel("Medico: ");
    private JLabel lbSuscriptor = new JLabel("Matricula del Suscriptor: ");
    private JLabel lbRazon = new JLabel("Razon: ");
    private JTextField txtFecha = new JTextField();
    private JTextField txtSuscriptor = new JTextField();
    private JComboBox<Empleado> cbMedicos = new JComboBox<>();
    private JTextArea txtRazon = new JTextArea();
    private JButton btnRegistrarIncidente = new JButton("Registrar Incidente");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable jTable;
    private JScrollPane scrollPane;

    private IncidenteDAO incidenteDAO = new IncidenteDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private SuscriptorDAO suscriptorDAO = new SuscriptorDAO();

    public PanelInicidentes(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);

        int x = this.getWidth() / 2 - 200;
        int y = 20;

        lbFecha.setBounds(x,y,200,30);
        x+=200;
        txtFecha.setBounds(x,y,200,30);
        x-=200;
        y+=40;
        lbMedico.setBounds(x,y,200,30);
        x+=200;
        cbMedicos.setBounds(x,y,200,30);
        x-=200;
        y+=40;
        lbSuscriptor.setBounds(x,y,200,30);
        x+=200;
        txtSuscriptor.setBounds(x,y,200,30);
        x-=200;
        y+=40;
        lbRazon.setBounds(x,y,200,30);
        y+=40;
        txtRazon.setBounds(x,y,400,100);
        y+=110;
        btnRegistrarIncidente.setBounds(this.getWidth()/2 - 75,y,150,30);
        y+=40;

        cargarTabla();
        llenarCombobox();

        scrollPane.setBounds(0,y,this.getWidth() - 15, this.getHeight() - y);

        btnRegistrarIncidente.addActionListener(e -> {
            String fecha = txtFecha.getText();
            String razon = txtRazon.getText();
            int matricula = Integer.parseInt(txtSuscriptor.getText());
            Empleado medico = (Empleado) cbMedicos.getSelectedItem();
            Suscriptor suscriptor = suscriptorDAO.get(matricula);

            Incidente incidente = new Incidente(fecha,medico.CI(),suscriptor.Clientes_CI(),
                    suscriptor.Matricula(), suscriptor.Suscripciones_id(), razon);
            try {
                incidenteDAO.registrarIncidente(incidente);
                JOptionPane.showMessageDialog(null, "Se registró el incidente correctamente");
                limpiarDatos();
                leerDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido registrar el incidente");
                throw new RuntimeException(ex);
            }
        });

        this.add(lbFecha);
        this.add(lbMedico);
        this.add(lbSuscriptor);
        this.add(lbRazon);
        this.add(txtFecha);
        this.add(txtSuscriptor);
        this.add(cbMedicos);
        this.add(txtRazon);
        this.add(btnRegistrarIncidente);
        this.add(scrollPane);
    }

    private void cargarTabla(){
        for (String nombre: empleadoDAO.getColumnas()) {
            defaultTableModel.addColumn(nombre);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    private void leerDatos() {
        defaultTableModel.setRowCount(0);
        for (Incidente i: incidenteDAO.getIncidentes()) {
            defaultTableModel.addRow(i.getDatos());
        }
    }

    private void llenarCombobox(){
        for (Empleado e: empleadoDAO.getMedicos()) {
            cbMedicos.addItem(e);
        }
    }

    private void limpiarDatos(){
        txtRazon.setText("");
        txtSuscriptor.setText("");
        txtFecha.setText("");
    }


}
