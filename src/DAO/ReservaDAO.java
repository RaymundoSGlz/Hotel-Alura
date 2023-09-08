package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
