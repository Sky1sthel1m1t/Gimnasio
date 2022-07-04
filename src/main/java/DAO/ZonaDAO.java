package DAO;

import Modelo.Conexion;
import Modelo.Zona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ZonaDAO extends AbstractDao<Zona> {

    @Override
    public Zona get(int id) {
        Zona zona = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Zonas WHERE CI = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            if (rs.next()){
                zona = new Zona(rs.getInt("ID"),
                        rs.getString("Descripcion"),
                        rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return zona;
    }

    public ArrayList<Zona> getZonas(){
        Conexion conexion = Conexion.getInstance();
        ArrayList<Zona> zonas = new ArrayList<>();

        String comando = "SELECT * FROM Zonas";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Zona zona = new Zona(rs.getInt("ID"),
                        rs.getString("Descripcion"),
                        rs.getString("Nombre"));
                zonas.add(zona);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return zonas;
    }

    public void añadirZona(Zona zona){
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO Zonas VALUES(" + zona.ID() + "," +
                "'" + zona.Descripcion() + "'" + "," +
                "'" + zona.Nombre() + "'" + ")";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement, comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarZona(int ID){
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM Zonas WHERE ID = " + ID;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> columnas(){
        ArrayList<String> columnas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC Zonas";

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
}
