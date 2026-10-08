package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public Connection conexion() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tecnostore_db_SBA", "root", "Monty_28sba");
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return c;
    }
}
