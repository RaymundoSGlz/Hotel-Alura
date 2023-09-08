package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reserva;

public class ReservaDAO {

    private Connection conexion;

    public ReservaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void guardar(Reserva res) {
        String sql = "INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_de_pago)"
                + "VALUES(?,?,?,?)";
        try (PreparedStatement stm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stm.setDate(1, res.getFechaE());
            stm.setDate(2, res.getFechaS());
            stm.setString(3, res.getValor());
            stm.setString(4, res.getFormaPago());

            stm.executeUpdate();

            try (ResultSet rst = stm.getGeneratedKeys()) {
                while (rst.next()) {
                    res.setId(rst.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la reserva en la base de datos.", e);
        }
    }

    public List<Reserva> buscar() {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor,forma_de_pago FROM reservas";

            try (PreparedStatement stm = conexion.prepareStatement(sql)) {
                stm.execute();

                transFormarResultSetEnReserva(reservas, stm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> buscarId(String id) {
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor,forma_de_pago FROM reservas WHERE  id= ?";

            try (PreparedStatement stm = conexion.prepareStatement(sql)) {
                stm.setString(1, id);
                stm.execute();

                transFormarResultSetEnReserva(reservas, stm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transFormarResultSetEnReserva(List<Reserva> reservas, PreparedStatement stm) throws SQLException {
        try (ResultSet rst = stm.getResultSet()) {
            while (rst.next()) {
                Reserva producto = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4),
                        rst.getString(5));
                reservas.add(producto);
            }
        }
    }
}
