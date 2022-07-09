package Vista;

import DAO.AsistenDAO;
import DAO.ClaseDAO;
import DAO.SuscriptorDAO;
import Modelo.Asisten;
import Modelo.Clase;
import Modelo.ProporcionaAcceso;
import Modelo.Suscriptor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class PanelAsisten extends JPanel {
    private JLabel lbMatricula = new JLabel("Matricula: ");
    private JTextField txtMatricula = new JTextField();
    private JButton btnAgregarSuscriptor = new JButton("Agregar Suscriptor");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable jTable;
    private JScrollPane scrollPane;

    private Clase clase;
    private SuscriptorDAO suscriptorDAO = new SuscriptorDAO();
    private AsistenDAO asistenDAO = new AsistenDAO();

    public PanelAsisten(Clase clase){
        setSize(600,600);
        this.clase = clase;
        init1();
    }

    private void init1() {
        setLayout(null);

        int x = this.getWidth() /2 - 200;
        int y = 20;

        lbMatricula.setBounds(x,y,200,30);
        x+=200;
        txtMatricula.setBounds(x,y,200,30);
        y+=40;
        btnAgregarSuscriptor.setBounds(this.getWidth()/2 -75, y,150,30);
        y+=40;

        iniciarJtable();

        scrollPane.setBounds(0,y,this.getWidth() - 15, this.getHeight() - y);

        btnAgregarSuscriptor.addActionListener(e -> {
            int matricula = Integer.parseInt(txtMatricula.getText());

            Suscriptor suscriptor = suscriptorDAO.get(matricula);

            if (suscriptor != null){
                try {
                    asistenDAO.añadirAsistencia(clase,suscriptor);
                    leerDatos();
                    JOptionPane.showMessageDialog(null, "Se ha añadido al suscriptor con exito");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Suscriptor no encontrado");
            }

        });

        this.add(lbMatricula);
        this.add(txtMatricula);
        this.add(btnAgregarSuscriptor);
        this.add(scrollPane);
    }

    private void iniciarJtable(){
        for (String s:asistenDAO.columnas()) {
            defaultTableModel.addColumn(s);
        }

        leerDatos();

        jTable = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(jTable);
    }

    public void leerDatos(){
        defaultTableModel.setRowCount(0);
        for (Asisten asisten : asistenDAO.getAsistencia(clase)) {
            defaultTableModel.addRow(asisten.getDatos());
        }
    }
}
