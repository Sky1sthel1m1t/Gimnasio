package DAO;

import Modelo.Conexion;
import Modelo.Incidente;
import Modelo.Suscripcion;
import Modelo.Suscriptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class IncidenteDAO {

    public ArrayList<Incidente> getIncidentes(){
        ArrayList<Incidente> incidentes = new ArrayList<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM incidentes";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Incidente incidente = new Incidente(
                        rs.getString("Fecha"),
                        rs.getInt("Empleados"),
                        rs.getInt("Suscriptor_Clientes_CI"),
                        rs.getInt("Suscriptor_Matricula"),
                        rs.getInt("Suscriptor_Suscripciones_ID"),
                        rs.getString("Razon"));
                incidentes.add(incidente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return incidentes;
    }

    public void registrarIncidente(Incidente incidente) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO incidentes VALUES(" +
                "'" + incidente.fecha() + "'" + "," +
                incidente.Empleados_CI() + "," +
                incidente.Suscriptor_Clientes_CI() + "," +
                incidente.Suscriptor_Matricula() + "," +
                incidente.Suscriptor_Suscripciones_ID() + "," +
                incidente.razon() + ")";

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement,comando);

        conexion.desconectar();
    }



}
