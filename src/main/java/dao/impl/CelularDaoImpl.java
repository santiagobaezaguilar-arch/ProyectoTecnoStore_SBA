package dao.impl;

import config.ConexionDB;
import dao.CelularDao;
import model.Celular;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDaoImpl implements CelularDao {

    @Override
    public void crear(Celular celular) {
        String sql = "INSERT INTO celular (modelo, id_marca, id_sistema_operativo, id_gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, celular.getModelo());
            ps.setInt(2, celular.getIdMarca());
            ps.setInt(3, celular.getIdSistemaOperativo());
            ps.setInt(4, celular.getIdGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.executeUpdate();
            System.out.println("¡Celular creado y guardado exitosamente en la BD!");
        } catch (SQLException e) {
            System.out.println("Error al crear celular (verifica que los IDs de Marca, Gama y SO existan): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Celular celular) {
        String sql = "UPDATE celular SET modelo = ?, id_marca = ?, id_sistema_operativo = ?, id_gama = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, celular.getModelo());
            ps.setInt(2, celular.getIdMarca());
            ps.setInt(3, celular.getIdSistemaOperativo());
            ps.setInt(4, celular.getIdGama());
            ps.setDouble(5, celular.getPrecio());
            ps.setInt(6, celular.getStock());
            ps.setInt(7, celular.getId());
            ps.executeUpdate();
            System.out.println("¡Celular actualizado exitosamente en la BD!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar celular: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM celular WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("¡Celular eliminado correctamente de la base de datos!");
            } else {
                System.out.println("No se encontró ningún celular registrado con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar celular (es posible que tenga ventas asociadas): " + e.getMessage());
        }
    }

    @Override
    public Celular buscar(int id) {
        String sql = "SELECT * FROM celular WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        return null;
    }

    @Override
    public List<Celular> obtenerCelulares() {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celular";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Celular c = new Celular(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getInt("id_marca"),
                    rs.getInt("id_sistema_operativo"),
                    rs.getInt("id_gama"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar celulares: " + e.getMessage());
        }
        return lista;
    }
    
}
