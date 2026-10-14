package controller.Logueo;

import config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AuditoriaArchivo;
import view.validarTexto.Validaciones;

// Aca arrancamos la clase LogueoController, la encargada de validar el ingreso de los vendedores al sistema, bien aja pa q amarre :V xd
public class LogueoController {

    // Aca instanciamos el validador de texto pa que el papulin no meta babosadas vacias en los inputs, gg ez
    private final view.validarTexto.Validaciones valTexto = new view.validarTexto.Validaciones();
    // Aca traemos el archivo de auditoria pa chismosear y registrar si el man entra con exito o si la caga, wazaa
    private final AuditoriaArchivo auditoriaArchivo = new AuditoriaArchivo();

    // Aca declaramos el metodo iniciarSesion que devuelve el ID del vendedor si pasa la prueba, o -1 si falla, asi bien armado
    public int iniciarSesion() {
        System.out.println("\n--- INICIO DE SESIÓN (VENDEDOR) ---");
        // Aca pedimos el correo validando que no lo dejen vacio con babosadas, eso tilin
        String correo = valTexto.validarTextoNoVacio("Ingrese su correo de vendedor: ");
        // Aca pedimos la clave de acceso de forma segura, piolin
        String contraseña = valTexto.validarTextoNoVacio("Ingrese su contraseña: ");

        // Aca armamos la sentencia SQL con un INNER JOIN pa cruzar las tablas de vendedor y persona, bien pro xd
        String sql = "SELECT v.id FROM vendedor v INNER JOIN persona p ON v.id_persona = p.id WHERE p.correo = ? AND p.contraseña = ?";
        
        // Aca abrimos la conexion a la base de datos MySQL y preparamos la consulta con PreparedStatement pa evitar inyecciones raras, chiclebomba
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Aca metemos el correo en el primer signo de interrogacion, bien aja
            ps.setString(1, correo);
            // Aca metemos la clave en el segundo signo de interrogacion, gg ez
            ps.setString(2, contraseña);
            
            // Aca ejecutamos la consulta y atrapamos el resultado en un ResultSet, wazaa pa q amarre
            try (ResultSet rs = ps.executeQuery()) {
                // Si el ResultSet trae datos (si encontro el usuario y clave), aqui inserto esto y hago aquello para ello: validamos el acceso, bien aja :V
                if (rs.next()) {
                    int idVendedor = rs.getInt("id");
                    
                    // Aca registramos el exito del inicio de sesión en el archivo .dat de auditoria pa que quede la evidencia, piolin pa q amarre
                    auditoriaArchivo.registrarAccion(correo, "LOGIN", "Inicio de sesión exitoso como vendedor ID: " + idVendedor);
                    
                    System.out.println("¡Login exitoso! Bienvenido.");
                    // Devolvemos el ID del vendedor pa que la app sepa exactamente quien esta operando, xd
                    return idVendedor;
                }
            }
        } catch (SQLException e) {
            // Si pasa algun error o cancer con la base de datos, atrapamos la excepcion SQL, bien aja
            System.out.println("Error en la base de datos durante el login: " + e.getMessage());
        }

        // Si las credenciales fallan o hubo un error, dejamos el registro del intento fallido en el archivo .dat de auditoria, wazaa
        auditoriaArchivo.registrarAccion(correo, "LOGIN_FALLIDO", "Intento fallido de inicio de sesión con correo: " + correo);

        System.out.println("Credenciales incorrectas o el usuario no está registrado como vendedor.");
        // Devolvemos -1 indicando que el login fallo por completo, gg ez :V
        return -1;
    }
}