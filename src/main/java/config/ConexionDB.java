package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static Connection conexion = null;

    private static final String URL = "jdbc:mysql://localhost:3306/tecnostore_db_SBA";
    private static final String USER = "root";
    private static final String PASSWORD = "Monty_28sba";
    
    private ConexionDB() {
    }

    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);;
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }

//"C:\Users\HP\Documents\NetBeansProjects\proyectoTecnoStore_SBA"
}
