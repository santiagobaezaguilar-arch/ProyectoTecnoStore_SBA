package dao.impl;

import config.ConexionDB;
import dao.MarcaDao;
import model.Marca;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDaoImpl implements MarcaDao {

    @Override
    public void crear(Marca marca) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, marca.getNombre());
            ps.executeUpdate();
            System.out.println("¡Marca creada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al crear marca: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Marca marca) {
        String sql = "UPDATE marca SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getId());
            ps.executeUpdate();
            System.out.println("¡Marca actualizada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar marca: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM marca WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Marca eliminada correctamente!");
            } else {
                System.out.println("No se encontró una marca con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar marca (recuerda que si un celular la está usando, la BD la bloqueará): " + e.getMessage());
        }
    }

    @Override
    public Marca buscar(int id) {
        String sql = "SELECT * FROM marca WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        return null;
    }

    @Override
    public List<Marca> obtenerMarcas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNombre(rs.getString("nombre"));
                lista.add(marca);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar marcas: " + e.getMessage());
        }
        return lista;
    }
}
