package Vista;

import DAO.SuscripcionDAO;
import Modelo.Suscripcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelSuscripciones extends JPanel {

    private JLabel lbID = new JLabel("ID: ");
    private JLabel lbNombre = new JLabel("Nombre: ");
    private JLabel lbPrecio = new JLabel("Precio: ");
    private JTextField txtID = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextField txtPrecio = new JTextField();

    private JButton btnAgregarSuscripcion = new JButton("Agregar Suscripcion");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private SuscripcionDAO suscripcionDAO = new SuscripcionDAO();

    public PanelSuscripciones(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        this.setLayout(null);

        int x = this.getWidth() / 2 - 100;
        int y = 20;

        lbID.setBounds(x,y,100,30);
        x += 100;
        txtID.setBounds(x,y,150,30);
        y+=40;
        x-=100;
        lbNombre.setBounds(x,y,100,30);
        x+=100;
        txtNombre.setBounds(x,y,150,30);
        y+=40;
        x-=100;
        lbPrecio.setBounds(x,y,100,30);
        x+=100;
        txtPrecio.setBounds(x,y,150,30);

        x = this.getWidth() / 2 - 75;
        y+=40;

        btnAgregarSuscripcion.setBounds(x,y,150,30);
        btnAgregarSuscripcion.setMargin(new Insets(0,0,0,0));

        y+=40;
        llenarTabla();

        scrollPane.setBounds(0,y,(this.getWidth()-15), (this.getHeight() - y) -30);

        btnAgregarSuscripcion.addActionListener(e -> {
            String aux = txtID.getText();
            Suscripcion suscripcion;

            try {
                int id = Integer.parseInt(aux);
                double precio = Double.parseDouble(txtPrecio.getText());
                String nombre = txtNombre.getText();

                suscripcion = new Suscripcion(id,precio,nombre);

            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "El ID tienen que ser numeros");
                return;
            }

            suscripcionDAO.crearSuscripcion(suscripcion);
            JOptionPane.showMessageDialog(null, "La suscripcion se ha registrado correctamente");
            leerDatos();
            limpiarCampos();
        });

        this.add(lbID);
        this.add(lbPrecio);
        this.add(lbNombre);
        this.add(txtID);
        this.add(txtPrecio);
        this.add(txtNombre);
        this.add(scrollPane);
        this.add(btnAgregarSuscripcion);
    }

    private void llenarTabla(){
        for (String t: suscripcionDAO.columnas()) {
            defaultTableModel.addColumn(t);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    private void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Suscripcion suscripcion: suscripcionDAO.getSuscripciones()) {
            defaultTableModel.addRow(suscripcion.getDatos());
        }
    }

    private void limpiarCampos(){
        txtID.setText("");
        txtPrecio.setText("");
        txtNombre.setText("");
    }

}
