package dao.impl;

import config.ConexionDB;
import dao.CelularDao;
import model.Celular;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase CelularDaoImpl, la encargada de implementar el DAO pa manejar todas las operaciones CRUD de los celulares directo en MySQL, bien pro pa q amarre :V xd
public class CelularDaoImpl implements CelularDao {

    // Aca sobreescribimos el metodo crear pa insertar un nuevo celular en la base de datos usando un PreparedStatement, gg ez
    @Override
    public void crear(Celular celular) {
        // Aca armamos la sentencia SQL de insercion con los signos de interrogacion pa evitar inyecciones raras, bien seguro
        String sql = "INSERT INTO celular (modelo, id_marca, id_sistema_operativo, id_gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        // Aca abrimos la conexion y el PreparedStatement con try-with-resources pa que se cierren solos y no dejen fugas, eso tilin
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Aca asignamos cada valor del celular a su respectivo parametro en el SQL, bien ordenado
            ps.setString(1, celular.getModelo());
            ps.setInt(2, celular.getIdMarca());
            ps.setInt(3, celular.getIdSistemaOperativo());
            ps.setInt(4, celular.getIdGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            // Aca ejecutamos la actualizacion (INSERT) en la base de datos, chiclebomba
            ps.executeUpdate();
            System.out.println("¡Celular creado y guardado exitosamente en la BD!");
        } catch (SQLException e) {
            // Si hay algun error o cancer con la conexion o los IDs, lo atrapamos aca sin armar show, piolin pa q amarre
            System.out.println("Error al crear celular (verifica que los IDs de Marca, Gama y SO existan): " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo actualizar pa modificar los datos de un telefono existente en MySQL, bien pro xd
    @Override
    public void actualizar(Celular celular) {
        // Aca preparamos el SQL de UPDATE apuntando al ID especifico del celular, wazaa
        String sql = "UPDATE celular SET modelo = ?, id_marca = ?, id_sistema_operativo = ?, id_gama = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // Aca seteamos los nuevos valores y le metemos el ID al final del WHERE, bien aja
            ps.setString(1, celular.getModelo());
            ps.setInt(2, celular.getIdMarca());
            ps.setInt(3, celular.getIdSistemaOperativo());
            ps.setInt(4, celular.getIdGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.setInt(7, celular.getId());
            // Ejecutamos el UPDATE en la base de datos, gg ez
            ps.executeUpdate();
            System.out.println("¡Celular actualizado exitosamente en la BD!");
        } catch (SQLException e) {
            // Si truena la consulta SQL, atrapamos el error y avisamos al papulin, eso tilin
            System.out.println("Error al actualizar celular: " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo eliminar pa borrar un celular de la base de datos segun su ID, bien armado xd
    @Override
    public void eliminar(int id) {
        // Aca definimos el comando DELETE en SQL, bien limpio
        String sql = "DELETE FROM celular WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // Aca guardamos cuantas filas fueron afectadas al ejecutar el DELETE, chiclebomba
            int filasAfectadas = ps.executeUpdate();
            // Aqui inserto esto y hago aquello para ello: si borro al menos una fila, confirmamos exito; si no, avisamos que no existia, piolin
            if (filasAfectadas > 0) {
                System.out.println("¡Celular eliminado correctamente de la base de datos!");
            } else {
                System.out.println("No se encontró ningún celular registrado con ese ID.");
            }
        } catch (SQLException e) {
            // Atrapamos cualquier error por si el telefono tiene ventas asociadas que lo amarran a la BD, gg ez :V
            System.out.println("Error al eliminar celular (es posible que tenga ventas asociadas): " + e.getMessage());
        }
    }

    // Aca sobreescribimos el metodo buscar pa consultar un celular especifico usando su ID, wazaa
    @Override
    public Celular buscar(int id) {
        // Preparamos la consulta SELECT filtrando por ID, bien aja
        String sql = "SELECT * FROM celular WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // Si el ResultSet encuentra el registro (`rs.next()`), aqui inserto esto y hago aquello para ello: construimos y retornamos el objeto Celular, eso tilin
                if (rs.next()) {
                    return new Celular(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getInt("id_marca"),
                        rs.getInt("id_sistema_operativo"),
                        rs.getInt("id_gama"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar celular: " + e.getMessage());
        }
        // Si no encuentra nada o falla, devolvemos null pa que la aplicacion sepa que no existe, xd
        return null;
    }

    // Aca sobreescribimos el metodo obtenerCelulares pa traer la lista completa de todos los telefonos guardados en MySQL, bien pro
    @Override
    public List<Celular> obtenerCelulares() {
        // Inicializamos una lista vacia donde iremos guardando cada telefono, gg ez
        List<Celular> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de celulares, piolin pa q amarre
        String sql = "SELECT * FROM celular";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            // Recorremos cada fila del resultado con un while, bien organizado
            while (rs.next()) {
                // Creamos el objeto Celular con los datos de la fila actual de la BD, chiclebomba
                Celular c = new Celular(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getInt("id_marca"),
                    rs.getInt("id_sistema_operativo"),
                    rs.getInt("id_gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                // Añadimos el celular a la lista, eso tilin :V
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar celulares: " + e.getMessage());
        }
        // Retornamos la lista completa de celulares listica pa usarla en las vistas y controladores, wazaa xd
        return lista;
    }
}
