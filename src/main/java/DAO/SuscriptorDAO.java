package DAO;

import Modelo.Conexion;
import Modelo.Suscripcion;
import Modelo.Suscriptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SuscriptorDAO extends AbstractDao<Suscriptor>{
    @Override
    public Suscriptor get(int id) {
        Suscriptor suscriptor = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Suscriptor WHERE Matricula = " + id;

        try{
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);

            if (rs.next()){
                suscriptor = new Suscriptor(rs.getInt("Matricula"),
                        rs.getInt("Clientes_CI"),
                        rs.getInt("Suscripciones_id"),
                        rs.getInt("Empleados_CI"),
                        rs.getString("dtPrimeraInscripcion"),
                        rs.getString("dtInicioSuscripcion"),
                        rs.getString("dtFinalSuscripcion"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return suscriptor;
    }

    public ArrayList<Suscriptor> getSuscriptores(){
        ArrayList<Suscriptor> suscriptores = new ArrayList<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Suscriptor";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Suscriptor suscriptor = new Suscriptor(rs.getInt("Matricula"),
                        rs.getInt("Clientes_CI"),
                        rs.getInt("Suscripciones_id"),
                        rs.getInt("Empleados_CI"),
                        rs.getString("dtPrimeraInscripcion"),
                        rs.getString("dtInicioSuscripcion"),
                        rs.getString("dtFinalSuscripcion"));
                suscriptores.add(suscriptor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return suscriptores;
    }

    public void registrarSuscriptor(Suscriptor suscriptor, int dias){
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO Suscriptor VALUES(" + "default" + "," +
                suscriptor.Clientes_CI() + "," +
                suscriptor.Suscripciones_id() + "," +
                suscriptor.Empleados_CI() + "," +
                "current_date()" + "," +
                "current_date()" + "," +
                "date_add(current_date(), Interval " + dias + " day)" + ")";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();
    }

    public void eliminarSuscriptor(int Matricula, int Clientes_CI, int Suscripciones_id){
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM Suscriptor WHERE Matricula = " + Matricula + " AND " +
                "Clientes_CI = " + Clientes_CI + " AND " + "Suscripciones_id = " + Suscripciones_id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();
    }

    public ArrayList<String> columnas(){
        ArrayList<String> columnas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC Suscriptor";

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
}
