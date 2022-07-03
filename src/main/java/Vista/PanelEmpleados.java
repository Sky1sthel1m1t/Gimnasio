package Vista;

import DAO.EmpleadoDAO;
import Modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelEmpleados extends JPanel {

    private JButton btnMostrarTodos = new JButton("Mostrar Todos");
    private JButton btnAgregarEmpleado = new JButton("Agregar Empleado");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable jTable;
    private JScrollPane scrollPane;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public PanelEmpleados(Dimension dimension){
        setSize(dimension);
        init1();
    }

    private void init1() {
        this.setLayout(null);
    }

    private void cargarTabla(){
        ArrayList<String> columnas = empleadoDAO.getColumnas();
        for (String nombre: columnas) {
            defaultTableModel.addColumn(nombre);
        }

        for (Empleado empleado: empleadoDAO.getEmpleados()) {
            defaultTableModel.addRow(empleado.getDatos());
        }

    }




}
