package Vista;

import DAO.EmpleadoDAO;
import Modelo.Empleado;

import javax.swing.*;

public class PanelAgregarEmpleados extends JPanel {

    private JLabel lbCI = new JLabel("CI: ");
    private JLabel lbNombres = new JLabel("Nombres: ");
    private JLabel lbApellidos = new JLabel("Apellidos: ");
    private JLabel lbdtContratacion = new JLabel("Fecha de Contratacion: ");
    private JLabel lbSueldo = new JLabel("Sueldo: ");
    private JLabel lbHoraEntrada = new JLabel("Hora de Entrada: ");
    private JLabel lbHoraSalida = new JLabel("Hora de Salida: ");
    private JLabel lbClaseEmpleado = new JLabel("Clase empleado: ");
    private JLabel lbTipo = new JLabel("Tipo: ");

    private JTextField txtCI = new JTextField();
    private JTextField txtNombres = new JTextField();
    private JTextField txtApellidos = new JTextField();
    private JTextField txtdtContratacion = new JTextField();
    private JTextField txtSueldo = new JTextField();
    private JTextField txtHoraEntrada = new JTextField();
    private JTextField txtHoraSalida = new JTextField();
    private JTextField txtClaseEmpleado = new JTextField();
    private JTextField txtTipo = new JTextField();

    private JButton btnAgregar = new JButton("Registrar");

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    private PanelEmpleados panelEmpleados;

    public PanelAgregarEmpleados(PanelEmpleados p){
        init1();
        this.panelEmpleados = p;
    }

    private void init1() {
        this.setSize(600,500);
        this.setLayout(null);

        int x = this.getWidth()/2 - 150;
        int y = 20;

        lbCI.setBounds(x,y,150,30);
        lbCI.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbNombres.setBounds(x,y,150,30);
        lbNombres.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbApellidos.setBounds(x,y,150,30);
        lbApellidos.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbdtContratacion.setBounds(x,y,150,30);
        lbdtContratacion.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbSueldo.setBounds(x,y,150,30);
        lbSueldo.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbHoraEntrada.setBounds(x,y,150,30);
        lbHoraEntrada.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbHoraSalida.setBounds(x,y,150,30);
        lbHoraSalida.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbClaseEmpleado.setBounds(x,y,150,30);
        lbClaseEmpleado.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;
        lbTipo.setBounds(x,y,150,30);
        lbTipo.setHorizontalAlignment(JLabel.RIGHT);
        y+=40;

        btnAgregar.setBounds(this.getWidth()/2-50, y, 100,30);

        x += 150;
        y = 20;

        txtCI.setBounds(x,y, 150,30);
        y+=40;
        txtNombres.setBounds(x,y,150,30);
        y+=40;
        txtApellidos.setBounds(x,y,150,30);
        y+=40;
        txtdtContratacion.setBounds(x,y,150,30);
        y+=40;
        txtSueldo.setBounds(x,y,150,30);
        y+=40;
        txtHoraEntrada.setBounds(x,y,150,30);
        y+=40;
        txtHoraSalida.setBounds(x,y,150,30);
        y+=40;
        txtClaseEmpleado.setBounds(x,y,150,30);
        y+=40;
        txtTipo.setBounds(x,y,150,30);

        btnAgregar.addActionListener(e -> {
            int CI = Integer.parseInt(txtCI.getText());
            String nombres = txtNombres.getText();
            String apellidos = txtApellidos.getText();
            String fecha = txtdtContratacion.getText();
            double sueldo = Double.parseDouble(txtSueldo.getText());
            String horaEntrada = txtHoraEntrada.getText();
            String horaSalida = txtHoraSalida.getText();
            String claseEmpleado = txtClaseEmpleado.getText();
            String tipo = txtTipo.getText();


            Empleado aux = new Empleado(CI,nombres,apellidos,fecha,sueldo,
                    horaEntrada,horaSalida,claseEmpleado,tipo);
            empleadoDAO.registrarEmpleado(aux);
            panelEmpleados.leerDatos();
        });

        this.add(lbCI);
        this.add(lbNombres);
        this.add(lbApellidos);
        this.add(lbdtContratacion);
        this.add(lbSueldo);
        this.add(lbHoraEntrada);
        this.add(lbHoraSalida);
        this.add(lbClaseEmpleado);
        this.add(lbTipo);
        this.add(txtCI);
        this.add(txtNombres);
        this.add(txtApellidos);
        this.add(txtdtContratacion);
        this.add(txtSueldo);
        this.add(txtHoraEntrada);
        this.add(txtHoraSalida);
        this.add(txtClaseEmpleado);
        this.add(txtTipo);
        this.add(btnAgregar);
    }

}
