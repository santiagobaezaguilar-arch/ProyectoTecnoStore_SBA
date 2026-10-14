package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Aca creamos la clase principal para la conexion a la base de datos pa q amarre bien
public class ConexionDB {

    // Aca guardamos la conexion en una variable estatica, bien aja, para reusarla y q no quede flotando
    private static Connection conexion = null;

    // Aca definimos la ruta de la BD MySQL (tecnostore_db_SBA) aca en el puerto 3306 pa q conecte
    private static final String URL = "jdbc:mysql://localhost:3306/tecnostore_db_SBA";
    // Aca ponemos el usuario por defecto de la base de datos, bien aja, el poderoso root
    private static final String USER = "root";
    // Aca va la clave de la base de datos pa q entre sin problemas
    private static final String PASSWORD = "Monty_28sba"; 
    
    // Aca hacemos el constructor privado para aplicar Singleton y q nadie cree objetos con 'new', chiclebomba
    private ConexionDB() {
    }

    // Aca creamos el metodo publico para obtener la conexion activa pa q amarre todo el proyecto
    public static Connection getConexion() {
        try {
            // Aca revisamos si no hay conexion previa o si la anterior se cerro, aja bien asi
            if (conexion == null || conexion.isClosed()) {
                // Aca intentamos conectar con la URL, el usuario y la clave, gg ez si no hay fallos
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            // Aca si ocurre algun cancer con la BD o la clave esta mal, atrapamos el error pa q no se rompa la app
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        // Aca devolvemos la conexion lista y armada pa q las consultas funcionen de una, papulin
        return conexion;
    }
}
