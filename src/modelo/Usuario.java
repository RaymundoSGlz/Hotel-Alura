package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factory.ConexionDB;

public class Usuario {
    private String nombre;
    private String contraseña;

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    /**
     * Método para validar un usuario en la base de datos.
     *
     * @return true si el usuario y la contraseña coinciden en la base de datos,
     *         false en caso contrario.
     */
    public static boolean validarUsuario(String nombre, String contraseña) {
        try {
            ConexionDB conexionDB = new ConexionDB();
            Connection connection = conexionDB.conectar();
            PreparedStatement state = connection
                    .prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?");

            state.setString(1, nombre);
            state.setString(2, contraseña);
            try (ResultSet result = state.executeQuery()) {
                return result.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
