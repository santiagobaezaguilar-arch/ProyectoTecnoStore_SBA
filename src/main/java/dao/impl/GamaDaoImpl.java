package dao.impl;

import config.ConexionDB;
import dao.GamaDao;
import model.Gama;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GamaDaoImpl implements GamaDao {

    @Override
    public void crear(Gama gama) {
        String sql = "INSERT INTO gama (nombre) VALUES (?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gama.getNombre());
            ps.executeUpdate();
            System.out.println("¡Gama creada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al crear gama: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Gama gama) {
        String sql = "UPDATE gama SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gama.getNombre());
            ps.setInt(2, gama.getId());
            ps.executeUpdate();
            System.out.println("¡Gama actualizada exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar gama: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM gama WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("¡Gama eliminada correctamente!");
            } else {
                System.out.println("No se encontró una gama con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar gama: " + e.getMessage());
        }
    }

    @Override
    public Gama buscar(int id) {
        String sql = "SELECT * FROM gama WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        return null;
    }

    @Override
    public List<Gama> obtenerGamas() {
        List<Gama> lista = new ArrayList<>();
        String sql = "SELECT * FROM gama";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Gama gama = new Gama();
                gama.setId(rs.getInt("id"));
                gama.setNombre(rs.getString("nombre"));
                lista.add(gama);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar gamas: " + e.getMessage());
        }
        return lista;
    }
}