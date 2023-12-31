package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class HuespedDAO {

    private Connection conexion;

    public HuespedDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardar(Huesped huesped) {
        try {
            String sql = "INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,"
                    + "nacionalidad,telefono,id_reserva) VALUES(?,?,?,?,?,?)";
            try (PreparedStatement stm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stm.setString(1, huesped.getNombre());
                stm.setString(2, huesped.getApellido());
                stm.setDate(3, huesped.getFechaNacimiento());
                stm.setString(4, huesped.getNacionalidad());
                stm.setString(5, huesped.getTelefono());
                stm.setInt(6, huesped.getIdReserva());

                stm.execute();

                try (ResultSet rst = stm.getGeneratedKeys()) {
                    while (rst.next()) {
                        huesped.setId(rst.getInt(1));
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> buscar() {
        List<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            String sql = "SELECT id, nombre , apellido, fecha_nacimiento, nacionalidad,telefono, id_reserva FROM huespedes";

            try (PreparedStatement stm = conexion.prepareStatement(sql)) {

                stm.execute();

                transFormarResultSetEnReserva(huespedes, stm);
            }
            return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> buscarId(String id) {
        List<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            String sql = "SELECT id, nombre , apellido, fecha_nacimiento,nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva=?";

            try (PreparedStatement stm = conexion.prepareStatement(sql)) {
                stm.setString(1, id);
                stm.execute();

                transFormarResultSetEnReserva(huespedes, stm);
            }
            return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Huesped> buscarApellidoHuesped(String apellido) {
        List<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            String sql = "SELECT id, nombre , apellido, fecha_nacimiento,nacionalidad, telefono, id_reserva FROM huespedes WHERE apellido=?";

            try (PreparedStatement stm = conexion.prepareStatement(sql)) {
                stm.setString(1, apellido);
                stm.execute();

                transFormarResultSetEnReserva(huespedes, stm);
            }
            return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transFormarResultSetEnReserva(List<Huesped> huespedes, PreparedStatement stm) throws SQLException {
        try (ResultSet rst = stm.getResultSet()) {
            while (rst.next()) {
                Huesped producto = new Huesped(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4),
                        rst.getString(5), rst.getString(6), rst.getInt(7));
                huespedes.add(producto);
            }
        }
    }

    public void actualizar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
            int idReserva, int id) {
        String sql = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, id_reserva = ? WHERE id = ?";
        try (PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, nombre);
            stm.setString(2, apellido);
            stm.setDate(3, fechaNacimiento);
            stm.setString(4, nacionalidad);
            stm.setString(5, telefono);
            stm.setInt(6, idReserva);
            stm.setInt(7, id);

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el huesped en la base de datos.", e);
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM huespedes WHERE id = ?";
        try (PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, id);

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el huesped en la base de datos.", e);
        }
    }

    public void eliminarPorReserva(int idReserva) {
        String sql = "DELETE FROM huespedes WHERE id_reserva = ?";
        try (PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, idReserva);

            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el huesped en la base de datos.", e);
        }
    }

}
