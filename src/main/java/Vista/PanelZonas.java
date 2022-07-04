package Vista;

import DAO.ZonaDAO;
import Modelo.Zona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelZonas extends JPanel {

    private JLabel lbID = new JLabel("ID: ");
    private JLabel lbNombre = new JLabel("Nombre: ");
    private JLabel lbDescripcion = new JLabel("Descripcion: ");
    private JTextField txtID = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JTextArea txtDescripcion = new JTextArea();
    private JButton btnAgregarZona = new JButton("Agregar Zona");

    private DefaultTableModel defaultTableModelZonas = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable jTableZonas;
    private JScrollPane scrollPaneZonas;

    private ZonaDAO zonaDAO = new ZonaDAO();

    public PanelZonas(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        this.setLayout(null);

        int x = this.getWidth() / 2 - 100;
        int y = 20;

        lbID.setBounds(x,y,75,30);
        x += 75;
        txtID.setBounds(x,y,200,30);
        y+=40;
        x-=75;
        lbNombre.setBounds(x,y,75,30);
        x+=75;
        txtNombre.setBounds(x,y,200,30);
        y+=40;
        x-=75;
        lbDescripcion.setBounds(x,y,100,30);
        y+=40;
        txtDescripcion.setBounds(x,y,275,50);

        x = this.getWidth() / 2 - 50;
        y+=60;

        btnAgregarZona.setBounds(x,y,100,30);
        btnAgregarZona.setMargin(new Insets(0,0,0,0));

        y+=40;

        llenarTabla();

        scrollPaneZonas.setBounds(0,y,this.getWidth() - 15,(this.getHeight() - y) - 30);

        btnAgregarZona.addActionListener(e -> {
            String aux = txtID.getText().trim();
            int ID;
            try{
                ID = Integer.parseInt(aux);
            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "El ID tienen que ser números");
                return;
            }

            String descripcion = txtDescripcion.getText();
            String nombre = txtNombre.getText();

            Zona zona = new Zona(ID, descripcion,nombre);
            zonaDAO.añadirZona(zona);
            JOptionPane.showMessageDialog(null, "Se ha añadido la zona correctamente");
            limpiarCampos();
            leerDatos();
        });

        this.add(lbID);
        this.add(lbDescripcion);
        this.add(lbNombre);
        this.add(txtID);
        this.add(txtDescripcion);
        this.add(txtNombre);
        this.add(scrollPaneZonas);
        this.add(btnAgregarZona);
    }

    private void llenarTabla(){
        for (String t: zonaDAO.columnas()) {
            defaultTableModelZonas.addColumn(t);
        }

        leerDatos();

        jTableZonas = new JTable(defaultTableModelZonas);
        scrollPaneZonas = new JScrollPane(jTableZonas);
    }

    private void leerDatos(){
        defaultTableModelZonas.setRowCount(0);
        for (Zona zona: zonaDAO.getZonas()) {
            defaultTableModelZonas.addRow(zona.getDatos());
        }
    }

    private void limpiarCampos(){
        txtID.setText("");
        txtDescripcion.setText("");
        txtNombre.setText("");
    }

}
