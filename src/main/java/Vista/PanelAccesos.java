package Vista;

import DAO.ProporcionaAccesoDAO;
import DAO.SuscripcionDAO;
import DAO.ZonaDAO;
import Modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class PanelAccesos extends JPanel {

    private JLabel lbZona = new JLabel("Zona: ");
    private JLabel lbSuscripcion = new JLabel("Suscripcion: " );
    private JComboBox<Zona> cbZonas = new JComboBox<>();
    private JComboBox<Suscripcion> cbSuscripciones = new JComboBox<>();
    private JButton btnPermitirAcceso = new JButton("Permitir acceso");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private ZonaDAO zonaDAO = new ZonaDAO();
    private SuscripcionDAO suscripcionDAO = new SuscripcionDAO();
    private ProporcionaAccesoDAO proporcionaAccesoDAO = new ProporcionaAccesoDAO();

    public PanelAccesos(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);
        int x = this.getWidth() / 2 - 200;
        int y = 20;

        lbSuscripcion.setBounds(x,y,200,30);
        x+=200;
        cbSuscripciones.setBounds(x,y,200,30);
        y+=40;
        x-=200;
        lbZona.setBounds(x,y,200,30);
        x+=200;
        cbZonas.setBounds(x,y,200,30);
        y+=40;
        btnPermitirAcceso.setBounds(this.getWidth()/2 - 75, y,150, 30);
        y+=40;

        iniciarJtable();
        llenarComboBox();

        scrollPane.setBounds(0,y,this.getWidth() - 15, this.getHeight() - y );

        btnPermitirAcceso.addActionListener(e -> {
            int idSuscripcion = ((Suscripcion)cbSuscripciones.getSelectedItem()).ID();
            int idZonas = ((Zona) cbZonas.getSelectedItem()).ID();

            ProporcionaAcceso acceso = new ProporcionaAcceso(idSuscripcion, idZonas);
            try {
                proporcionaAccesoDAO.añadirAcceso(acceso);
                JOptionPane.showMessageDialog(null, "Se ha concedido acceso correctamente");
                leerDatos();
            } catch (Exception error){
                JOptionPane.showMessageDialog(null,"La suscripcion elegida ya tiene acceso a esa zona!");
            }

        });

        this.add(lbSuscripcion);
        this.add(lbZona);
        this.add(cbSuscripciones);
        this.add(cbZonas);
        this.add(btnPermitirAcceso);
        this.add(scrollPane);
    }

    private void iniciarJtable(){
        defaultTableModel.addColumn("Suscripcion");
        defaultTableModel.addColumn("Zona");

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = jTable.getSelectedRow();

                    int idSuscripcion = Integer.parseInt(((String) defaultTableModel.getValueAt(fila, 0)));
                    int idZona = Integer.parseInt(((String) defaultTableModel.getValueAt(fila, 1)));

                    Suscripcion suscripcion = suscripcionDAO.get(idSuscripcion);
                    Zona zona = zonaDAO.get(idZona);

                    int respuesta = JOptionPane.showConfirmDialog(null, "Quiere eliminar el acceso" +
                            " que tiene la suscripcion " + suscripcion.Nombre() + " a la zona " + zona.Nombre() + "?");

                    if (respuesta == JOptionPane.YES_OPTION){
                        try{
                            proporcionaAccesoDAO.eliminarAcceso(idSuscripcion,idZona);
                            JOptionPane.showMessageDialog(null, "Se ha eliminado el acceso correctamente");
                            leerDatos();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "No se ha podido eliminar el acceso");
                        }
                    }

                }
            }
        });
    }

    private void llenarComboBox(){
        for (Zona zona: zonaDAO.getZonas()) {
            cbZonas.addItem(zona);
        }
        for (Suscripcion suscripcion: suscripcionDAO.getSuscripciones()) {
            cbSuscripciones.addItem(suscripcion);
        }
    }

    public void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (ProporcionaAcceso acceso : proporcionaAccesoDAO.getAccesos()) {
            defaultTableModel.addRow(acceso.getDatos());
        }
    }
}
