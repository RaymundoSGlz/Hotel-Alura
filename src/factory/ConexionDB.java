package factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConexionDB implements AutoCloseable {
    private DataSource dataSource;

    public ConexionDB() {
        ComboPooledDataSource comboPool = new ComboPooledDataSource();
        comboPool.setJdbcUrl("jdbc:mysql://localhost:3306/hotelAlura");
        comboPool.setUser("root");
        comboPool.setPassword("frost");

        this.dataSource = comboPool;
    }

    public Connection conectar() {
        try {
            return this.dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (dataSource instanceof ComboPooledDataSource) {
            ((ComboPooledDataSource) dataSource).close(); // Cierra la fuente de datos.
        }
    }
}
