package DAO;

import Modelo.Conexion;
import Modelo.Empleado;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDAO extends AbstractDao<Empleado>{

    public EmpleadoDAO(){

    }

    @Override
    public Empleado get(int id) {
        Conexion conexion = Conexion.getInstance();
        Empleado empleado = null;

        String comando = "SELECT * FROM Empleados WHERE CI = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            if (rs.next()){
                empleado = new Empleado(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("dtContratacion"),
                        rs.getDouble("Sueldo"),
                        rs.getString("HoraEntrada"),
                        rs.getString("HoraSalida"),
                        rs.getString("ClaseEmpleado"),
                        rs.getString("tipo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return empleado;
    }

    public ArrayList<Empleado> getEmpleados(){
        Conexion conexion = Conexion.getInstance();
        ArrayList<Empleado> empleados = new ArrayList<>();

        String comando = "SELECT * FROM Empleados";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Empleado empleado = new Empleado(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("dtContratacion"),
                        rs.getDouble("Sueldo"),
                        rs.getString("HoraEntrada"),
                        rs.getString("HoraSalida"),
                        rs.getString("ClaseEmpleado"),
                        rs.getString("tipo"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return empleados;
    }

    public void registrarEmpleado(Empleado empleado){
        Conexion conexion = Conexion.getInstance();

        String comando;

        if (empleado.tipo() != null){
            comando = "INSERT INTO Empleados VALUES(" +  empleado.CI() + "," + "'" +  empleado.nombre() + "'"
                    + "," + "'" + empleado.apellidos() + "'" + "," + "'" + empleado.dtContratacion() + "'" + "," + empleado.sueldo()
                    + "," +  "'" + empleado.HoraEntrada() + "'" + "," + "'" +  empleado.HoraSalida() + "'" + "," + "'" + empleado.claseEmpleado() + "'"
                    + "," + "'" + empleado.tipo() + "'" + ")";
        } else {
            comando = "INSERT INTO Empleados VALUES(" +  empleado.CI() + "," + "'" +  empleado.nombre() + "'"
                    + "," + "'" + empleado.apellidos() + "'" + "," + "'" + empleado.dtContratacion() + "'" + "," + empleado.sueldo()
                    + "," +  "'" + empleado.HoraEntrada() + "'" + "," + "'" +  empleado.HoraSalida() + "'" + "," + "'" + empleado.claseEmpleado() + "'"
                    + "," + null + ")";
        }

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement, comando);
            JOptionPane.showMessageDialog(null,"Se registró exitosamente al empleado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se ha podido registrar al empleado");
            throw new RuntimeException(e);
        }

        conexion.desconectar();
    }

    public void despedirEmpleado(int CI){
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM Empleados WHERE CI = " + CI;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();
    }

    public ArrayList<String> getColumnas(){
        ArrayList<String> columnas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC Empleados";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);
            while (rs.next()){
                String aux = rs.getString("Field");
                columnas.add(aux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return columnas;
    }

    public ArrayList<Empleado> getRecepcionistas(){
        ArrayList<Empleado> recepcionistas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "select * from empleados where ClaseEmpleado = 'Recepcionista'";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);
            while (rs.next()){
                Empleado empleado = new Empleado(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("dtContratacion"),
                        rs.getDouble("Sueldo"),
                        rs.getString("HoraEntrada"),
                        rs.getString("HoraSalida"),
                        rs.getString("ClaseEmpleado"),
                        rs.getString("tipo"));
                recepcionistas.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return recepcionistas;
    }

    public ArrayList<Empleado> getMedicos(){
        ArrayList<Empleado> medicos = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "select * from empleados where ClaseEmpleado = 'Medico General'";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);
            while (rs.next()){
                Empleado empleado = new Empleado(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("dtContratacion"),
                        rs.getDouble("Sueldo"),
                        rs.getString("HoraEntrada"),
                        rs.getString("HoraSalida"),
                        rs.getString("ClaseEmpleado"),
                        rs.getString("tipo"));
                medicos.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medicos;
    }

    public ArrayList<Empleado> getNutricionistas(){
        ArrayList<Empleado> nutricionistas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "select * from empleados where ClaseEmpleado = 'Nutricionista'";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);
            while (rs.next()){
                Empleado empleado = new Empleado(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("dtContratacion"),
                        rs.getDouble("Sueldo"),
                        rs.getString("HoraEntrada"),
                        rs.getString("HoraSalida"),
                        rs.getString("ClaseEmpleado"),
                        rs.getString("tipo"));
                nutricionistas.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nutricionistas;
    }



    //        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //        Date date = new Date();
    //        Empleado empleado = new Empleado(3,"test", "test",
    //        dateFormat.format(date),1500.00, "10:00:00",
    //        "16:00:00", "Recepcionista", null);
}
