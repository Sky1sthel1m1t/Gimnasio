package DAO;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.Horario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HorarioDAO extends AbstractDao<Horario>{

    @Override
    public Horario get(int id) {
        Horario horario = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Horarios WHERE ID = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement,comando);

            if (rs.next()){
                horario = new Horario(rs.getInt("ID"),
                        rs.getString("Dia"),
                        rs.getString("HoraInicio"),
                        rs.getString("HoraFinal"),
                        rs.getInt("Empleados_CI"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return horario;
    }

    public ArrayList<Horario> getHorarios(){
        Conexion conexion = Conexion.getInstance();
        ArrayList<Horario> horarios = new ArrayList<>();

        String comando = "SELECT * FROM Horarios";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()){
                Horario horario = new Horario(rs.getInt("ID"),
                        rs.getString("Dia"),
                        rs.getString("HoraInicio"),
                        rs.getString("HoraFinal"),
                        rs.getInt("Empleados_CI"));
                horarios.add(horario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return horarios;
    }

    public void registrarHorario(Horario horario){
        Conexion conexion = Conexion.getInstance();

        String comando = "INSERT VALUES Horarios VALUES (" + horario.ID() + "," +
                "'" + horario.Dia() + "'" + "," +
                "'" + horario.HoraInicio() + "'" + "," +
                "'" + horario.HoraFinal() + "'" + "," +
                horario.Empleados_CI() + ")";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement,comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
