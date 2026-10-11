package controller.Logueo;

import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import view.validarTexto.Validaciones;

public class LogueoController {

    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();

    // Retorna el ID del vendedor si las credenciales son correctas, de lo contrario -1
    public int iniciarSesion() {
        System.out.println("\n--- INICIO DE SESIÓN (VENDEDOR) ---");
        String correo = valTexto.validarTextoNoVacio("Ingrese su correo de vendedor: ");
        String contraseña = valTexto.validarTextoNoVacio("Ingrese su contraseña: ");

        String sql = "SELECT v.id FROM vendedor v INNER JOIN persona p ON v.id_persona = p.id WHERE p.correo = ? AND p.contraseña = ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idVendedor = rs.getInt("id");
                    System.out.println("¡Login exitoso! Bienvenido.");
                    return idVendedor;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos durante el login: " + e.getMessage());
        }

        System.out.println("❌ Credenciales incorrectas o el usuario no está registrado como vendedor.");
        return -1;
    }
}
