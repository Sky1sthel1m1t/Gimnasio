package Vista;

import DAO.ClienteDAO;
import DAO.EmpleadoDAO;
import DAO.SuscripcionDAO;
import DAO.SuscriptorDAO;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Suscripcion;
import Modelo.Suscriptor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class PanelSuscriptores extends JPanel {

    private JLabel lbCI = new JLabel("CI: ");
    private JLabel lbNombre = new JLabel("Nombre: ");
    private JLabel lbApellidos = new JLabel("Apellidos: ");
    private JLabel lbTelefono = new JLabel("Telefono: ");
    private JLabel lbMesesSuscripcion = new JLabel("Meses de suscripcion: ");
    private JLabel lbSuscripciones = new JLabel("Suscripciones: ");
    private JLabel lbRecepcionista = new JLabel("Recepcionista: ");
    private JTextField txtCI = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtApellidos = new JTextField();
    private JTextField txtTelefono = new JTextField();
    private JTextField txtMesesSuscripcion = new JTextField();
    private JButton btnRegistrarSuscriptor = new JButton("Registrar");

    private JComboBox<Suscripcion> cbSuscripciones = new JComboBox<>();
    private JComboBox<Empleado> cbRecepcionistas = new JComboBox<>();

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private SuscripcionDAO suscripcionDAO = new SuscripcionDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private SuscriptorDAO suscriptorDAO = new SuscriptorDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public PanelSuscriptores(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);

        int x = this.getWidth() / 2 - 150;
        int y = 10;

        lbCI.setBounds(x,y,150,30);
        x+=150;
        txtCI.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbNombre.setBounds(x,y,150,30);
        x+=150;
        txtNombre.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbApellidos.setBounds(x,y,150,30);
        x+=150;
        txtApellidos.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbTelefono.setBounds(x,y,150,30);
        x+=150;
        txtTelefono.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbMesesSuscripcion.setBounds(x,y,150,30);
        x+=150;
        txtMesesSuscripcion.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbSuscripciones.setBounds(x,y,150,30);
        x+=150;
        cbSuscripciones.setBounds(x,y,150,30);
        x-=150;
        y+=35;
        lbRecepcionista.setBounds(x,y,150,30);
        x+=150;
        cbRecepcionistas.setBounds(x,y,150,30);
        y+=35;
        btnRegistrarSuscriptor.setBounds(this.getWidth() /2 - 50,y,100,30);
        y+=35;

        llenarCBRecepcionistas();
        llenarCBSuscripciones();
        iniciarJtable();

        scrollPane.setBounds(0,y,this.getWidth() - 15, this.getHeight() - y);

        btnRegistrarSuscriptor.addActionListener(e -> {
            int CI = Integer.parseInt(txtCI.getText());
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String telefono = txtApellidos.getText();
            int dias = Integer.parseInt(txtMesesSuscripcion.getText()) * 30;

            try {
                Cliente cliente = new Cliente(CI, nombre,apellidos,telefono);
                clienteDAO.registrarCliente(cliente);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se puede volver a registrar a una persona!");
                return;
            }

            Suscripcion suscripcion = (Suscripcion) cbSuscripciones.getSelectedItem();
            Empleado empleado = (Empleado) cbRecepcionistas.getSelectedItem();

            Suscriptor suscriptor = new Suscriptor(CI, suscripcion.ID(), empleado.CI());
            suscriptorDAO.registrarSuscriptor(suscriptor,dias);

            JOptionPane.showMessageDialog(null, "Se registró al suscriptor correctamente");
            limpiarDatos();
        });

        this.add(lbCI);
        this.add(lbNombre);
        this.add(lbApellidos);
        this.add(lbTelefono);
        this.add(lbMesesSuscripcion);
        this.add(lbRecepcionista);
        this.add(lbSuscripciones);
        this.add(txtCI);
        this.add(txtNombre);
        this.add(txtApellidos);
        this.add(txtTelefono);
        this.add(txtMesesSuscripcion);
        this.add(cbRecepcionistas);
        this.add(cbSuscripciones);
        this.add(scrollPane);
        this.add(btnRegistrarSuscriptor);
    }

    private void llenarCBSuscripciones(){
        for (Suscripcion s: suscripcionDAO.getSuscripciones()) {
            cbSuscripciones.addItem(s);
        }
    }

    private void llenarCBRecepcionistas(){
        for (Empleado e: empleadoDAO.getRecepcionistas()) {
            cbRecepcionistas.addItem(e);
        }
    }

    private void iniciarJtable(){
        for (String s: suscriptorDAO.columnas()) {
            defaultTableModel.addColumn(s);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    private void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Suscriptor s: suscriptorDAO.getSuscriptores()) {
            defaultTableModel.addRow(s.getDatos());
        }
    }

    private void limpiarDatos(){
        txtCI.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtMesesSuscripcion.setText("");
    }

}
