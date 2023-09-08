package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) throws SQLException {
        ConexionDB conexion = new ConexionDB();
        Connection con = conexion.conectar();

        System.out.println("Conexión exitosa");

        con.close();

        System.out.println("Conexión cerrada");

    }
}
