package DAO;

import Modelo.Cliente;
import Modelo.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteDAO extends AbstractDao<Cliente> {

    public ClienteDAO() {
    }

    @Override
    public Cliente get(int id) {
        Cliente cliente = null;
        Conexion conexion = Conexion.getInstance();

        String comando = "SELECT * FROM Clientes WHERE CI = " + id;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            if (rs.next()) {
                cliente = new Cliente(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Telefono"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return cliente;
    }

    public ArrayList<Cliente> getClientes() {
        Conexion conexion = Conexion.getInstance();
        ArrayList<Cliente> clientes = new ArrayList<>();

        String comando = "SELECT * FROM Clientes";

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            ResultSet rs = conexion.consulta(statement, comando);

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("CI"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Telefono"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();

        return clientes;
    }

    public void registrarCliente(Cliente cliente) throws SQLException {
        Conexion conexion = Conexion.getInstance();

        String comando;

        if (cliente.Telefono() != null) {
            comando = "INSERT INTO Clientes VALUES(" + cliente.CI() + ","
                    + "'" + cliente.Nombres() + "'" + ","
                    + "'" + cliente.Apellidos() + "'" + ","
                    + "'" + cliente.Telefono() + "'" + ")";
        } else {
            comando = "INSERT INTO Clientes VALUES(" + cliente.CI() + ","
                    + "'" + cliente.Nombres() + "'" + ","
                    + "'" + cliente.Apellidos() + "'" + ","
                    + null + ")";
        }

        conexion.conectar();
        Statement statement = conexion.getConexion().createStatement();
        conexion.ejecutar(statement, comando);


        conexion.desconectar();
    }

    public void borrarCliente(int CI) {
        Conexion conexion = Conexion.getInstance();

        String comando = "DELETE FROM Clientes WHERE CI = " + CI;

        try {
            conexion.conectar();
            Statement statement = conexion.getConexion().createStatement();
            conexion.ejecutar(statement, comando);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conexion.desconectar();
    }
}
