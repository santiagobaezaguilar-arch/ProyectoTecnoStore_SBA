package dao.impl;

import config.ConexionDB;
import dao.PersonaDao;
import model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase PersonaDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de los clientes y personas en MySQL, bien pro pa q amarre :V xd
public class ERROR implements PersonaDao {

    // Aca sobreescribimos el metodo crear pa insertar un nuevo usuario en la base de datos usando un PreparedStatement, gg ez
    @Override
    public void crear(Persona persona) {
        // Preparamos la sentencia SQL de insercion pa guardar los datos personales evitando inyecciones raras, bien seguro
        String sql = "INSERT INTO persona (nombre, identificacion, correo, contraseña, telefono) VALUES (?, ?, ?, ?, ?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solitos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos cada campo del usuario en los signos de interrogacion del SQL, bien ordenado
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getIdentificacion());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getContraseña());
            ps.setString(5, persona.getTelefono());
            // Ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("¡Persona creada y guardada exitosamente en la BD!");
        } catch (SQLException e) {
            // Si hay algun error o la identificacion esta repetida, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al crear persona (verifica que la identificación no esté repetida): " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo actualizar pa modificar los datos de un usuario existente en MySQL, bien pro xd
    @Override
    public void actualizar(Persona persona) {
        // Preparamos el SQL de UPDATE apuntando al ID especifico de la persona, wazaa
        String sql = "UPDATE persona SET nombre = ?, identificacion = ?, correo = ?, contraseña = ?, telefono = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos los nuevos valores y le metemos el ID al final del WHERE, bien aja
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getIdentificacion());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getContraseña());
            ps.setString(5, persona.getTelefono());
            ps.setInt(6, persona.getId());
            // Ejecutamos el UPDATE en la base de datos, gg ez
            ps.executeUpdate();
            System.out.println("¡Persona actualizada exitosamente en la BD!");
        } catch (SQLException e) {
            // Atrapamos cualquier error SQL y avisamos al papulin, eso tilin
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar una persona de la base de datos segun su ID, bien armado xd
    @Override
    public void eliminar(int id) {
        // Definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM persona WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Guardamos cuantas filas fueron afectadas al ejecutar el borrado, chiclebomba
            int filas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, piolin
            if (filas > 0) {
                System.out.println("¡Persona eliminada correctamente!");
            } else {
                System.out.println("No se encontró ninguna persona con ese ID.");
            }
        } catch (SQLException e) {
            // Detecta si es un error de restricción de llave foránea (integridad referencial) con el codigo SQLState o de MySQL, gg ez :V
            if (e.getSQLState().startsWith("23") || e.getErrorCode() == 1451) {
                System.out.println("No se puede eliminar esta persona porque tiene ventas registradas o un rol de vendedor asociado.");
            } else {
                System.out.println("Ocurrió un error al intentar eliminar el registro.");
            }
        }
    }

    // Aca sobreescribimos el metodo buscar pa consultar una persona especifica usando su ID, wazaa
    @Override
    public Persona buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM persona WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto Persona completo, eso tilin
                if (rs.next()) {
                    return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e.getMessage());
        }
        // Si no encuentra nada, devolvemos null pa que la app sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo obtenerPersonas pa listar todos los usuarios guardados en MySQL, piolin pa q amarre
    @Override
    public List<Persona> obtenerPersonas() {
        // Inicializamos una lista vacia donde iremos guardando cada persona, gg ez
        List<Persona> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de personas, wazaa
        String sql = "SELECT * FROM persona";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                Persona p = new Persona(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("correo"),
                    rs.getString("contraseña"),
                    rs.getString("telefono")
                );
                // Añadimos la persona a la lista, eso tilin :V
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar personas: " + e.getMessage());
        }
        // Retornamos la lista completa de personas listica pa usarla en las vistas y controladores, chiclebomba xd
        return lista;
    }
}