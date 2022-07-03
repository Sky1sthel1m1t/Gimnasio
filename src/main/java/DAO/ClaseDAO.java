package DAO;

import Modelo.Clase;
import Modelo.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClaseDAO extends AbstractDao<Clase>{
    @Override
    public Clase get(int id) {
        Clase clase = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Clases WHERE CI = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);

            if (rs.next()){
                clase = new Clase(rs.getString("Fecha"),
                        rs.getInt("Zonas_ID"),
                        rs.getInt("Horarios_ID")
                        );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clase;
    }

    public ArrayList<Clase> getClases(){
        Conexion conexion = Conexion.getInstance();
        ArrayList<Clase> clases = new ArrayList<>();

        String comando = "SELECT * FROM Clases";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Clase clase = new Clase(rs.getString("Fecha"),
                        rs.getInt("Zonas_ID"),
                        rs.getInt("Horarios_ID")
                );
                clases.add(clase);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clases;
    }

    public void programarClase(Clase clase){
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO Clases VALUES("
                + "'" + clase.Fecha() + "'" + "," +
                clase.Zonas_ID() + "," +
                clase.Horarios_ID() + ")";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
