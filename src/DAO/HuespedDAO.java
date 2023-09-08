package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
