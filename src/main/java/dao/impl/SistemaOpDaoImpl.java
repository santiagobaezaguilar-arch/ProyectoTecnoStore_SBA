package dao.impl;

import config.ConexionDB;
import dao.SistemaOpDao;
import model.SistemOp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaOpDaoImpl implements SistemaOpDao {

    @Override
    public void crear(SistemOp sistemaOp) {
        String sql = "INSERT INTO sistema_operativo (nombre) VALUES (?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sistemaOp.getNombre());
            ps.executeUpdate();
            System.out.println("¡Sistema Operativo creado exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al crear sistema operativo: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(SistemOp sistemaOp) {
        String sql = "UPDATE sistema_operativo SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sistemaOp.getNombre());
            ps.setInt(2, sistemaOp.getId());
            ps.executeUpdate();
            System.out.println("¡Sistema Operativo actualizado exitosamente!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar sistema operativo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM sistema_operativo WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("¡Sistema Operativo eliminado correctamente!");
            } else {
                System.out.println("No se encontró un sistema operativo con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar sistema operativo: " + e.getMessage());
        }
    }

    @Override
    public SistemOp buscar(int id) {
        String sql = "SELECT * FROM sistema_operativo WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        return null;
    }

    @Override
    public List<SistemOp> obtenerSistemasOp() {
        List<SistemOp> lista = new ArrayList<>();
        String sql = "SELECT * FROM sistema_operativo";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                SistemOp so = new SistemOp();
                so.setId(rs.getInt("id"));
                so.setNombre(rs.getString("nombre"));
                lista.add(so);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar sistemas operativos: " + e.getMessage());
        }
        return lista;
    }
}