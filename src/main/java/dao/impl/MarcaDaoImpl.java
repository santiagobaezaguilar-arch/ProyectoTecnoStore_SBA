package dao.impl;

import config.ConexionDB;
import dao.MarcaDao;
import model.Marca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase MarcaDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de las marcas de telefonos en MySQL, bien pro pa q amarre :V xd
public class MarcaDaoImpl implements MarcaDao {

    // Aca sobreescribimos el metodo crear pa insertar una nueva marca en la base de datos usando un PreparedStatement, gg ez
    @Override
    public void crear(Marca marca) {
        // Preparamos la sentencia SQL de insercion pa evitar inyecciones raras, bien seguro
        String sql = "INSERT INTO marca (nombre) VALUES (?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solitos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el nombre de la marca en el signo de interrogacion del SQL, bien ordenado
            ps.setString(1, marca.getNombre());
            // Ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("¡Marca creada exitosamente!");
        } catch (SQLException e) {
            // Si hay algun error o cancer con la conexion, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al crear marca: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo actualizar pa modificar el nombre de una marca existente en MySQL, bien pro xd
    @Override
    public void actualizar(Marca marca) {
        // Preparamos el SQL de UPDATE apuntando al ID de la marca, wazaa
        String sql = "UPDATE marca SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el nuevo nombre y el ID en el WHERE, bien aja
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            // Ejecutamos el UPDATE en la base de datos, gg ez
            ps.executeUpdate();
            System.out.println("¡Marca actualizada exitosamente!");
        } catch (SQLException e) {
            // Atrapamos cualquier error SQL y avisamos al papulin, eso tilin
            System.out.println("Error al actualizar marca: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar una marca de la base de datos segun su ID, bien armado xd
    @Override
    public void eliminar(int id) {
        // Definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM marca WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Guardamos cuantas filas fueron afectadas al ejecutar el borrado, chiclebomba
            int filasAfectadas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, piolin
            if (filasAfectadas > 0) {
                System.out.println("¡Marca eliminada correctamente!");
            } else {
                System.out.println("No se encontró una marca con ese ID.");
            }
        } catch (SQLException e) {
            // Atrapamos cualquier error por si la marca tiene celulares asociados que la amarran a la BD, gg ez :V
            System.out.println("Error al eliminar marca (recuerda que si un celular la está usando, la BD la bloqueará): " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo buscar pa consultar una marca especifica usando su ID, wazaa
    @Override
    public Marca buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM marca WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto Marca, eso tilin
                if (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("id"));
                    marca.setNombre(rs.getString("nombre"));
                    return marca;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar marca: " + e.getMessage());
        }
        // Si no encuentra nada, devolvemos null pa que la app sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo obtenerMarcas pa listar todas las marcas guardadas en MySQL, piolin pa q amarre
    @Override
    public List<Marca> obtenerMarcas() {
        // Inicializamos una lista vacia donde iremos guardando cada marca, gg ez
        List<Marca> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de marcas, wazaa
        String sql = "SELECT * FROM marca";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                // Añadimos la marca a la lista, eso tilin :V
                lista.add(marca);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar marcas: " + e.getMessage());
        }
        // Retornamos la lista completa de marcas listica pa usarla en las vistas y controladores, chiclebomba xd
        return lista;
    }
}
