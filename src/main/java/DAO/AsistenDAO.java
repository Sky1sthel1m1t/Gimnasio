package DAO;

import Modelo.Asisten;
import Modelo.Clase;
import Modelo.Conexion;
import Modelo.Suscriptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AsistenDAO{

    public ArrayList<Asisten> getAsistencia(Clase clase){
        ArrayList<Asisten> asisten = new ArrayList<>();
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM asisten WHERE Clases_Fecha = " + "'" + clase.Fecha() + "'" +
                " and Clases_Horarios_ID = " + clase.Horarios_ID() +
                " and Clases_Zonas_ID = " + clase.Zonas_ID();

        System.out.println(comando);

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Asisten asisten1 = new Asisten(rs.getString("Clases_Fecha"),
                        rs.getInt("Clases_Horarios_ID"),
                        rs.getInt("Clases_Zonas_ID"),
                        rs.getInt("Suscriptor_Matricula"),
                        rs.getInt("Suscriptor_Clientes_CI"),
                        rs.getInt("Suscriptor_Suscripciones_ID"));
                asisten.add(asisten1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return asisten;
    }

    public void añadirAsistencia(Clase clase, Suscriptor suscriptor) throws SQLException {
        Conexion conexion = Conexion.getInstance();
        String comando = "INSERT INTO asisten VALUES(" + "'" + clase.Fecha() + "'" +  "," +
                clase.Horarios_ID() + "," +
                clase.Zonas_ID() + "," +
                suscriptor.Matricula() + "," +
                suscriptor.Clientes_CI() + "," +
                suscriptor.Suscripciones_id() + ")";

        System.out.println(comando);

            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);


        conexion.desconectar();
    }

    public ArrayList<String> columnas(){
        ArrayList<String> columnas = new ArrayList<>();

        Conexion conexion = Conexion.getInstance();
        String comando = "DESC asisten";

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
