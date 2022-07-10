package DAO;

import Modelo.Cita;
import Modelo.Conexion;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CitasDao {

    public ArrayList<Cita> getCitas() {
        ArrayList<Cita> citas = new ArrayList<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM citas";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getString("Fecha"),
                        rs.getInt("Suscriptor_Matricula"),
                        rs.getInt("Suscriptor_Clientes_CI"),
                        rs.getInt("Suscriptor_Suscripciones_ID"),
                        rs.getInt("Empleados_CI"),
                        rs.getString("Hora"),
                        rs.getDouble("Peso"),
                        rs.getString("Observaciones")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return citas;
    }

    public void registrarCita(Cita cita) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO citas VALUES(" +
                "'" + cita.fecha() + "'" + "," +
                cita.Suscriptor_Matricula() + "," +
                cita.Suscriptor_Clientes_CI() + "," +
                cita.Suscriptor_Suscripciones_ID() + "," +
                cita.Empleados_CI() + "," +
                "'" + cita.hora() + "'" + "," +
                cita.peso() + "," +
                "'" + cita.observaciones() + "'" + ")";

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();
    }

    public ArrayList<String> getColumnas() {
        ArrayList<String> columnas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC Citas";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);
            while (rs.next()) {
                String aux = rs.getString("Field");
                columnas.add(aux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return columnas;
    }

}
