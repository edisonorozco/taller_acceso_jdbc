package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    protected Connection conexion;

    //JDBC Driver adapters
    private final String JDBC_DRIVER_POSTGRESQL = "org.postgresql.Driver";
    private final String DB_POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/db_acceso_jdbc";

    //Base de datos credenciales
    private final String USER_POSTGRESQL = "postgres";
    private final String PASS_POSTGRESQL = "root";

    public void conectar() {
        try {
            conexion = DriverManager.getConnection(DB_POSTGRESQL_URL, USER_POSTGRESQL, PASS_POSTGRESQL);
            Class.forName(JDBC_DRIVER_POSTGRESQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrar() throws SQLException {
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }
    }
}
