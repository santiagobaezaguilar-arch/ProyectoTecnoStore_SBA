package dao.impl;

import config.ConexionDB;
import dao.VendedorDao;
import model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase VendedorDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de los vendedores y cajeros en MySQL, bien pro pa q amarre :V xd
public class VendedorDaoImpl implements VendedorDao {

    // Aca sobreescribimos el metodo crear pa insertar un nuevo vendedor en la base de datos usando un PreparedStatement, gg ez
    @Override
    public void crear(Vendedor vendedor) {
        // Preparamos la sentencia SQL de insercion pa guardar el empleado evitando inyecciones raras, bien seguro
        String sql = "INSERT INTO vendedor (id_persona, rol) VALUES (?, ?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solitos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos el ID de la persona y el rol en los signos de interrogacion del SQL, bien ordenado
            ps.setInt(1, vendedor.getIdPersona());
            ps.setString(2, vendedor.getRol());
            // Ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("Vendedor registrado exitosamente en la base de datos.");
        } catch (SQLException e) {
            // Si hay algun error o cancer con la conexion o los IDs, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al registrar vendedor: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo obtenerVendedores pa listar todos los empleados registrados en MySQL, bien pro xd
    @Override
    public List<Vendedor> obtenerVendedores() {
        // Inicializamos una lista vacia donde iremos guardando cada vendedor, gg ez
        List<Vendedor> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de vendedores, wazaa
        String sql = "SELECT * FROM vendedor";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                // Creamos el objeto Vendedor con los datos de la fila actual y lo añadimos a la lista, bien aja
                lista.add(new Vendedor(
                    rs.getInt("id"),
                    rs.getInt("id_persona"),
                    rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de vendedores: " + e.getMessage());
        }
        // Retornamos la lista completa de vendedores listica pa usarla en las vistas y controladores, chiclebomba xd
        return lista;
    }

    // Aca sobreescribimos el metodo buscar pa consultar un vendedor especifico usando su ID, wazaa
    @Override
    public Vendedor buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM vendedor WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto Vendedor, eso tilin
                if (rs.next()) {
                    return new Vendedor(
                        rs.getInt("id"),
                        rs.getInt("id_persona"),
                        rs.getString("rol")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el vendedor: " + e.getMessage());
        }
        // Si no encuentra nada, devolvemos null pa que la app sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo actualizar pa modificar los datos de un vendedor existente en MySQL, bien armado xd
    @Override
    public void actualizar(Vendedor vendedor) {
        // Preparamos el SQL de UPDATE apuntando al ID especifico del vendedor, gg ez
        String sql = "UPDATE vendedor SET id_persona = ?, rol = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Seteamos los nuevos valores y le metemos el ID al final del WHERE, bien ordenado
            ps.setInt(1, vendedor.getIdPersona());
            ps.setString(2, vendedor.getRol());
            ps.setInt(3, vendedor.getId());
            // Ejecutamos el UPDATE en la base de datos, piolin pa q amarre
            ps.executeUpdate();
            System.out.println("Vendedor actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el vendedor: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar un vendedor de la base de datos segun su ID, bien pro xd
    @Override
    public void eliminar(int id) {
        // Definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM vendedor WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Guardamos cuantas filas fueron afectadas al ejecutar el borrado, chiclebomba
            int filas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, eso tilin
            if (filas > 0) {
                System.out.println("Vendedor eliminado correctamente.");
            } else {
                System.out.println("No se encontro ningun vendedor con ese ID.");
            }
        } catch (SQLException e) {
            // Control de integridad referencial (por si tiene ventas asociadas y la BD lo bloquea), detectando el codigo SQLState o de MySQL, gg ez :V
            if (e.getSQLState().startsWith("23") || e.getErrorCode() == 1451) {
                System.out.println("No se puede eliminar este vendedor porque tiene ventas asociadas.");
            } else {
                System.out.println("Ocurrio un error al intentar eliminar el registro.");
            }
        }
    }
}