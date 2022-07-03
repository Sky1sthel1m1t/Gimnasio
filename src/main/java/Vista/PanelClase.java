package Vista;

import DAO.ClaseDAO;
import DAO.HorarioDAO;
import DAO.ZonaDAO;
import Modelo.Clase;
import Modelo.Horario;
import Modelo.Zona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelClase extends JPanel {

    private JLabel lbFecha = new JLabel("Fecha: ");
    private JLabel lbZonasID = new JLabel("Zona: ");
    private JLabel lbHorarioID = new JLabel("Horario: ");

    private JComboBox<Zona> cbZonas = new JComboBox<>();
    private JComboBox<Horario> cbHorario = new JComboBox<>();
    private JTextField txtFecha = new JTextField();

    private JButton btnCrearClase = new JButton("Crear Clase");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable jTable;
    private JScrollPane scrollPane;

    private ClaseDAO claseDAO = new ClaseDAO();

    private HorarioDAO horarioDAO = new HorarioDAO();
    private ZonaDAO zonaDAO = new ZonaDAO();

    public PanelClase(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        setLayout(null);

        int x = this.getWidth()/2-200;
        int y = 20;

        lbFecha.setBounds(x,y,100,30);
        x+=100;
        txtFecha.setBounds(x,y,200,30);
        y+=40;
        x-=100;
        lbHorarioID.setBounds(x,y,100,30);
        x+=100;
        cbHorario.setBounds(x,y,200,30);
        y+=40;
        x-=100;
        lbZonasID.setBounds(x,y,100,30);
        x+=100;
        cbZonas.setBounds(x,y,200,30);
        y+=40;
        x = this.getWidth()/2 - 50;
        btnCrearClase.setBounds(x,y,100,30);
        y+=40;

        btnCrearClase.addActionListener(e -> {
            Horario horario = (Horario) cbHorario.getSelectedItem();
            Zona zona = (Zona) cbZonas.getSelectedItem();
            String fecha = txtFecha.getText();

            if (zona == null){
                JOptionPane.showMessageDialog(null, "Se debe seleccionar una zona para poder" +
                        " crear la clase");
                return;
            }

            Clase clase = new Clase(fecha,zona.ID(),horario.ID());

            claseDAO.programarClase(clase);
            JOptionPane.showMessageDialog(null, "Se ha creado la clase correctamente");
            leerDatos();
        });

        llenarComboBox();
        llenarTabla();

        scrollPane.setBounds(0,y,
                this.getWidth() - 15, (this.getHeight() - (y - 30)));

        this.add(lbFecha);
        this.add(lbHorarioID);
        this.add(lbZonasID);
        this.add(cbHorario);
        this.add(cbZonas);
        this.add(txtFecha);
        this.add(btnCrearClase);
        this.add(scrollPane);
    }

    private void llenarTabla(){
        for (String t: claseDAO.columnas()) {
            defaultTableModel.addColumn(t);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    public void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Clase clase: claseDAO.getClases()) {
            defaultTableModel.addRow(clase.getDatos());
        }
    }

    private void llenarComboBox(){
        for (Horario horario: horarioDAO.getHorarios()) {
            cbHorario.addItem(horario);
        }

        if (zonaDAO.getZonas().size() == 0){
            cbZonas.setEnabled(false);
        } else {
            for (Zona zona: zonaDAO.getZonas()) {
                cbZonas.addItem(zona);
            }
        }
    }






}
