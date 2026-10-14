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

public class VendedorDaoImpl implements VendedorDao {

    @Override
    public void crear(Vendedor vendedor) {
        String sql = "INSERT INTO vendedor (id_persona, rol) VALUES (?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vendedor.getIdPersona());
            ps.setString(2, vendedor.getRol());
            ps.executeUpdate();
            System.out.println("Vendedor registrado exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al registrar vendedor: " + e.getMessage());
        }
    }

    @Override
    public List<Vendedor> obtenerVendedores() {
        List<Vendedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM vendedor";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Vendedor(
                    rs.getInt("id"),
                    rs.getInt("id_persona"),
                    rs.getString("rol")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de vendedores: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Vendedor buscar(int id) {
        String sql = "SELECT * FROM vendedor WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        return null;
    }

    @Override
    public void actualizar(Vendedor vendedor) {
        String sql = "UPDATE vendedor SET id_persona = ?, rol = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vendedor.getIdPersona());
            ps.setString(2, vendedor.getRol());
            ps.setInt(3, vendedor.getId());
            ps.executeUpdate();
            System.out.println("Vendedor actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el vendedor: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM vendedor WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Vendedor eliminado correctamente.");
            } else {
                System.out.println("No se encontro ningun vendedor con ese ID.");
            }
        } catch (SQLException e) {
            // Control de integridad referencial para evitar mostrar errores tecnicos de SQL
            if (e.getSQLState().startsWith("23") || e.getErrorCode() == 1451) {
                System.out.println("No se puede eliminar este vendedor porque tiene ventas asociadas.");
            } else {
                System.out.println("Ocurrio un error al intentar eliminar el registro.");
            }
        }
    }
}
