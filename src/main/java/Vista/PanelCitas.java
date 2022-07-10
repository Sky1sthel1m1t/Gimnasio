package Vista;

import DAO.CitasDao;
import DAO.EmpleadoDAO;
import DAO.SuscriptorDAO;
import Modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class PanelCitas extends JPanel {

    private JLabel lbFecha = new JLabel("Fecha: ");
    private JLabel lbSuscriptor = new JLabel("Matricula del suscriptor: ");
    private JLabel lbNutricionista = new JLabel("Nutricionista: ");
    private JLabel lbHora = new JLabel("Hora: ");
    private JLabel lbPeso = new JLabel("Peso: ");
    private JLabel lbObservaciones = new JLabel("Observaciones: ");
    private JTextField txtFecha = new JTextField();
    private JTextField txtSuscriptor = new JTextField();
    private JTextField txtHora = new JTextField();
    private JTextField txtPeso = new JTextField();
    private JTextArea txtObservaciones = new JTextArea();
    private JComboBox<Empleado> cbNutricionistas = new JComboBox<>();
    private JButton btnRegistrarCita = new JButton("Registrar Cita");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private SuscriptorDAO suscriptorDAO = new SuscriptorDAO();
    private CitasDao citasDao = new CitasDao();

    public PanelCitas(Dimension dimension){
        setSize(dimension);
        init1();

    }

    private void init1() {
        setLayout(null);
        int x = this.getWidth()/2 - 200;
        int y = 5;

        lbFecha.setBounds(x,y,200,30);
        x+=200;
        txtFecha.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbSuscriptor.setBounds(x,y,200,30);
        x+=200;
        txtSuscriptor.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbNutricionista.setBounds(x,y,200,30);
        x+=200;
        cbNutricionistas.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbHora.setBounds(x,y,200,30);
        x+=200;
        txtHora.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbPeso.setBounds(x,y,200,30);
        x+=200;
        txtPeso.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbObservaciones.setBounds(x,y,200,30);
        y+=40;
        txtObservaciones.setBounds(x,y,400,70);
        y+=80;
        btnRegistrarCita.setBounds(this.getWidth()/2 -75, y, 150, 30);
        y+=40;

        llenarTabla();
        llenarComboBox();

        scrollPane.setBounds(0,y,this.getWidth()-15, this.getHeight() - y);

        btnRegistrarCita.addActionListener(e -> {
            String fecha = txtFecha.getText();
            int matricula = Integer.parseInt(txtSuscriptor.getText());
            Suscriptor suscriptor = suscriptorDAO.get(matricula);

            if (suscriptor == null){
                JOptionPane.showMessageDialog(null,"Suscriptor no encontrado");
                return;
            }

            Empleado empleado = (Empleado) cbNutricionistas.getSelectedItem();
            String hora = txtHora.getText();
            double peso = Double.parseDouble(txtPeso.getText());
            String observaciones = txtObservaciones.getText();

            Cita cita = new Cita(fecha,
                    suscriptor.Matricula(),
                    suscriptor.Clientes_CI(),
                    suscriptor.Suscripciones_id(),
                    empleado.CI(),
                    hora,peso,observaciones);
            try {
                citasDao.registrarCita(cita);
                JOptionPane.showMessageDialog(null, "Se registró la cita con exito");
                leerDatos();
                limpiarDatos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido registrar la cita");
                throw new RuntimeException(ex);
            }
        });

        this.add(lbFecha);
        this.add(lbSuscriptor);
        this.add(lbNutricionista);
        this.add(lbHora);
        this.add(lbPeso);
        this.add(lbObservaciones);
        this.add(txtFecha);
        this.add(txtSuscriptor);
        this.add(cbNutricionistas);
        this.add(txtHora);
        this.add(txtPeso);
        this.add(txtObservaciones);
        this.add(btnRegistrarCita);
        this.add(scrollPane);
    }

    private void llenarTabla(){
        for (String t: citasDao.getColumnas()) {
            defaultTableModel.addColumn(t);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    public void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Cita c : citasDao.getCitas()) {
            defaultTableModel.addRow(c.getDatos());
        }
    }

    private void llenarComboBox(){
        for (Empleado e: empleadoDAO.getNutricionistas()) {
            cbNutricionistas.addItem(e);
        }
    }

    private void limpiarDatos(){
        txtObservaciones.setText("");
        txtPeso.setText("");
        txtHora.setText("");
        txtSuscriptor.setText("");
        txtFecha.setText("");
    }

}
