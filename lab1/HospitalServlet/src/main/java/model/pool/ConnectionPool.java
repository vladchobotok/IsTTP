package model.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    private static final HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(resourceBundle.getString("url"));
        config.setUsername(resourceBundle.getString("user"));
        config.setPassword(resourceBundle.getString("password"));
        config.setMaximumPoolSize(15);
        config.setDriverClassName(resourceBundle.getString("driver"));
        ds = new HikariDataSource( config );
    }

    private ConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
