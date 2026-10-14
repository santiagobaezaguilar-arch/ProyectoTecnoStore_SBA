package dao.impl;

import config.ConexionDB;
import dao.SistemaOpDao;
import model.SistemOp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase SistemaOpDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de los sistemas operativos en MySQL, bien pro pa q amarre :V xd
public class SistemaOpDaoImpl implements SistemaOpDao {

    // Aca sobreescribimos el metodo crear pa insertar un nuevo sistema operativo en la base de datos usando un PreparedStatement, gg ez
    @Override
    public void crear(SistemOp sistemaOp) {
        // Preparamos la sentencia SQL de insercion pa guardar el SO evitando inyecciones raras, bien seguro
        String sql = "INSERT INTO sistema_operativo (nombre) VALUES (?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solitos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el nombre del sistema operativo en el signo de interrogacion del SQL, bien ordenado
            ps.setString(1, sistemaOp.getNombre());
            // Ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("¡Sistema Operativo creado exitosamente!");
        } catch (SQLException e) {
            // Si hay algun error o cancer con la conexion, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al crear sistema operativo: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo actualizar pa modificar el nombre de un sistema operativo existente en MySQL, bien pro xd
    @Override
    public void actualizar(SistemOp sistemaOp) {
        // Preparamos el SQL de UPDATE apuntando al ID especifico del SO, wazaa
        String sql = "UPDATE sistema_operativo SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el nuevo nombre y el ID en el WHERE, bien aja
            ps.setString(1, sistemaOp.getNombre());
            ps.setInt(2, sistemaOp.getId());
            // Ejecutamos el UPDATE en la base de datos, gg ez
            ps.executeUpdate();
            System.out.println("¡Sistema Operativo actualizado exitosamente!");
        } catch (SQLException e) {
            // Atrapamos cualquier error SQL y avisamos al papulin, eso tilin
            System.out.println("Error al actualizar sistema operativo: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar un sistema operativo de la base de datos segun su ID, bien armado xd
    @Override
    public void eliminar(int id) {
        // Definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM sistema_operativo WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Guardamos cuantas filas fueron afectadas al ejecutar el borrado, chiclebomba
            int filas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, piolin
            if (filas > 0) {
                System.out.println("¡Sistema Operativo eliminado correctamente!");
            } else {
                System.out.println("No se encontró un sistema operativo con ese ID.");
            }
        } catch (SQLException e) {
            // Atrapamos cualquier error por si el SO tiene telefonos asociados que lo amarran a la BD, gg ez :V
            System.out.println("Error al eliminar sistema operativo: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo buscar pa consultar un sistema operativo especifico usando su ID, wazaa
    @Override
    public SistemOp buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM sistema_operativo WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto SistemOp, eso tilin
                if (rs.next()) {
                    SistemOp so = new SistemOp();
                    so.setId(rs.getInt("id"));
                    so.setNombre(rs.getString("nombre"));
                    return so;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar sistema operativo: " + e.getMessage());
        }
        // Si no encuentra nada, devolvemos null pa que la app sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo obtenerSistemasOp pa listar todos los sistemas operativos guardados en MySQL, piolin pa q amarre
    @Override
    public List<SistemOp> obtenerSistemasOp() {
        // Inicializamos una lista vacia donde iremos guardando cada SO, gg ez
        List<SistemOp> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de sistemas operativos, wazaa
        String sql = "SELECT * FROM sistema_operativo";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                SistemOp so = new SistemOp();
                so.setId(rs.getInt("id"));
                so.setNombre(rs.getString("nombre"));
                // Añadimos el SO a la lista, eso tilin :V
                lista.add(so);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar sistemas operativos: " + e.getMessage());
        }
        // Retornamos la lista completa de sistemas operativos listica pa usarla en las vistas y controladores, chiclebomba xd
        return lista;
    }
}