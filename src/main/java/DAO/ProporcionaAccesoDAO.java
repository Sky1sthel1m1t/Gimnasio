package DAO;

import Modelo.Conexion;
import Modelo.Horario;
import Modelo.ProporcionaAcceso;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProporcionaAccesoDAO extends AbstractDao<ProporcionaAcceso> {
    @Override
    public ProporcionaAcceso get(int idSuscripciones) {
        ProporcionaAcceso acceso = null;

        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM ProporcionaAcceso WHERE Suscripciones_id = " + idSuscripciones;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            if (rs.next()) {
                acceso = new ProporcionaAcceso(rs.getInt("Suscripciones_id"),
                        rs.getInt("Zonas_ID"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return acceso;
    }

    public ArrayList<ProporcionaAcceso> getAccesos() {
        ArrayList<ProporcionaAcceso> accesos = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();

        String comando = "Select * FROM ProporcionaAcceso";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()) {
                ProporcionaAcceso acceso = new ProporcionaAcceso(rs.getInt("Suscripciones_id"),
                        rs.getInt("Zonas_ID"));
                accesos.add(acceso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return accesos;
    }

    public void añadirAcceso(ProporcionaAcceso proporcionaAcceso) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT INTO proporcionaAcceso VALUES(" +
                proporcionaAcceso.suscripciones_id() + "," +
                proporcionaAcceso.zonas_id() + ")";

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();
    }

    public void eliminarAcceso(int idSus, int idZona) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM proporcionaAcceso where Suscripciones_id = " + idSus +
                " and Zonas_ID = " + idZona;

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);

        conexion.desconectar();
    }


}
