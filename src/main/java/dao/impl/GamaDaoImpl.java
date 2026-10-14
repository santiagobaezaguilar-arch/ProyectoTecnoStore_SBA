package dao.impl;

import config.ConexionDB;
import dao.GamaDao;
import model.Gama;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase GamaDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de las gamas de celulares en MySQL, bien pro pa q amarre :V xd
public class GamaDaoImpl implements GamaDao {

    // Aca sobreescribimos el metodo crear pa insertar una nueva gama en la base de datos con un PreparedStatement, gg ez
    @Override
    public void crear(Gama gama) {
        // Aca preparamos la sentencia SQL de insercion pa evitar inyecciones raras, bien seguro
        String sql = "INSERT INTO gama (nombre) VALUES (?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solitos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Aca seteamos el nombre de la gama en el signo de interrogacion del SQL, bien ordenado
            ps.setString(1, gama.getNombre());
            // Aca ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("¡Gama creada exitosamente!");
        } catch (SQLException e) {
            // Si hay algun error o cancer con la conexion, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al crear gama: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo actualizar pa modificar el nombre de una gama existente en MySQL, bien pro xd
    @Override
    public void actualizar(Gama gama) {
        // Preparamos el SQL de UPDATE apuntando al ID de la gama, wazaa
        String sql = "UPDATE gama SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el nuevo nombre y el ID en el WHERE, bien aja
            ps.setString(1, gama.getNombre());
            ps.setInt(2, gama.getId());
            // Ejecutamos el UPDATE en la base de datos, gg ez
            ps.executeUpdate();
            System.out.println("¡Gama actualizada exitosamente!");
        } catch (SQLException e) {
            // Atrapamos cualquier error SQL y avisamos al papulin, eso tilin
            System.out.println("Error al actualizar gama: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar una gama de la base de datos segun su ID, bien armado xd
    @Override
    public void eliminar(int id) {
        // Definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM gama WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Guardamos cuantas filas fueron afectadas al ejecutar el borrado, chiclebomba
            int filas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, piolin
            if (filas > 0) {
                System.out.println("¡Gama eliminada correctamente!");
            } else {
                System.out.println("No se encontró una gama con ese ID.");
            }
        } catch (SQLException e) {
            // Atrapamos cualquier error por si la gama tiene telefonos asociados que la amarran a la BD, gg ez :V
            System.out.println("Error al eliminar gama: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo buscar pa consultar una gama especifica usando su ID, wazaa
    @Override
    public Gama buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM gama WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto Gama, eso tilin
                if (rs.next()) {
                    Gama gama = new Gama();
                    gama.setId(rs.getInt("id"));
                    gama.setNombre(rs.getString("nombre"));
                    return gama;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar gama: " + e.getMessage());
        }
        // Si no encuentra nada, devolvemos null pa que la app sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo obtenerGamas pa listar todas las categorias guardadas en MySQL, piolin pa q amarre
    @Override
    public List<Gama> obtenerGamas() {
        // Inicializamos una lista vacia donde iremos guardando cada gama, gg ez
        List<Gama> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de gamas, wazaa
        String sql = "SELECT * FROM gama";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                Gama gama = new Gama();
                gama.setId(rs.getInt("id"));
                gama.setNombre(rs.getString("nombre"));
                // Añadimos la gama a la lista, eso tilin :V
                lista.add(gama);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar gamas: " + e.getMessage());
        }
        // Retornamos la lista completa de gamas listica pa usarla en las vistas y controladores, chiclebomba xd
        return lista;
    }
}