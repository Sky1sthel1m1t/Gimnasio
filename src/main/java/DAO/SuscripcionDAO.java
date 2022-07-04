package DAO;

import Modelo.Conexion;
import Modelo.Suscripcion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SuscripcionDAO extends AbstractDao<Suscripcion>{

    @Override
    public Suscripcion get(int id) {
        Suscripcion suscripciones = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Suscripciones WHERE ID = " + id;

        try{
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);

            if (rs.next()){
                suscripciones = new Suscripcion(rs.getInt("ID"),
                        rs.getDouble("Precio"),
                        rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suscripciones;
    }

    public ArrayList<Suscripcion> getSuscripciones(){
        ArrayList<Suscripcion> suscripciones = new ArrayList<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Suscripciones";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Suscripcion suscripcion = new Suscripcion(rs.getInt("ID"),
                        rs.getDouble("Precio"),
                        rs.getString("Nombre"));
                suscripciones.add(suscripcion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return suscripciones;
    }

    public void crearSuscripcion(Suscripcion suscripcion){
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO Suscripciones VALUES(" + suscripcion.ID() + "," +
                suscripcion.Precio() + "," +
                "'" + suscripcion.Nombre() + "'" + ")";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarSuscripcion(int ID){
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM Suscripciones WHERE ID = " + ID;

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
        String comando = "DESC Suscripciones";

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
