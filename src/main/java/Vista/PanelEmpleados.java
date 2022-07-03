package Vista;

import DAO.EmpleadoDAO;
import Modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PanelEmpleados extends JPanel {

    private JButton btnMostrarTodos = new JButton("Mostrar Todos");
    private JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
    private JButton btnDespedirEmpleado = new JButton("Despedir Empleado");

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
        cargarTabla();
        init1();
    }

    private void init1() {
        this.setLayout(null);

        int x = this.getWidth() /2 - 200;
        int y = 50;

        btnMostrarTodos.setBounds(x,y,150,30);
        x += 200;
        btnAgregarEmpleado.setBounds(x,y,150,30);
        x -= 100;
        y += 40;
        btnDespedirEmpleado.setBounds(x,y,150,30);
        y = this.getHeight() / 4;
        scrollPane.setBounds(0,y,this.getWidth() - 15, (this.getHeight() - y) - 38);

        btnAgregarEmpleado.addActionListener(e -> {
            PanelAgregarEmpleados aux = new PanelAgregarEmpleados(this);
            VentanaEmergente ventanaEmergente = new VentanaEmergente(aux);
        });

        btnMostrarTodos.addActionListener(e -> {
            leerDatos();
        });

        btnDespedirEmpleado.addActionListener(e -> {
            String aux = JOptionPane.showInputDialog("Ingrese el CI de la persona que quisiera despedir");
            try {
                int CI = Integer.parseInt(aux);
                empleadoDAO.despedirEmpleado(CI);
                leerDatos();
            } catch (Exception error){
                JOptionPane.showMessageDialog(null, "El CI solo son números");
            }
        });

        this.add(btnMostrarTodos);
        this.add(btnAgregarEmpleado);
        this.add(btnDespedirEmpleado);
        this.add(scrollPane);
    }

    private void cargarTabla(){
        ArrayList<String> columnas = empleadoDAO.getColumnas();

        for (String nombre: columnas) {
            defaultTableModel.addColumn(nombre);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = jTable.getSelectedRow();

                    String aux = (String) defaultTableModel.getValueAt(fila, 0);
                    int CI = Integer.parseInt(aux);

                    Empleado empleado = empleadoDAO.get(CI);

                    if (empleado.claseEmpleado().equalsIgnoreCase("Instructor")){
                        PanelInstructores panelInstructores = new PanelInstructores(empleado);
                        new VentanaEmergente(panelInstructores);
                    }
                }
            }
        });

        scrollPane = new JScrollPane(jTable);

        scrollPane.updateUI();
    }

    public void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Empleado empleado: empleadoDAO.getEmpleados()) {
            defaultTableModel.addRow(empleado.getDatos());
        }
    }




}
