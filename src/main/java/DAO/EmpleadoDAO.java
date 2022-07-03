package DAO;

import Modelo.Conexion;
import Modelo.Empleado;

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
        } // Si explota es porque borré el punto y coma del final

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement, comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        return columnas;
    }


    //        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //        Date date = new Date();
    //        Empleado empleado = new Empleado(3,"test", "test",
    //        dateFormat.format(date),1500.00, "10:00:00",
    //        "16:00:00", "Recepcionista", null);
}
