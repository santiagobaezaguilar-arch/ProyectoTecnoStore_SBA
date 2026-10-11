package dao.impl;

import config.ConexionDB;
import dao.PersonaDao;
import model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoImpl implements PersonaDao {

    @Override
    public void crear(Persona persona) {
        String sql = "INSERT INTO persona (nombre, identificacion, correo, contraseña, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getIdentificacion());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getContraseña());
            ps.setString(5, persona.getTelefono());
            ps.executeUpdate();
            System.out.println("¡Persona creada y guardada exitosamente en la BD!");
        } catch (SQLException e) {
            System.out.println("Error al crear persona (verifica que la identificación no esté repetida): " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE persona SET nombre = ?, identificacion = ?, correo = ?, contraseña = ?, telefono = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getIdentificacion());
            ps.setString(3, persona.getCorreo());
            ps.setString(4, persona.getContraseña());
            ps.setString(5, persona.getTelefono());
            ps.setInt(6, persona.getId());
            ps.executeUpdate();
            System.out.println("¡Persona actualizada exitosamente en la BD!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar persona: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("¡Persona eliminada correctamente!");
            } else {
                System.out.println("No se encontró ninguna persona con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar persona (puede tener ventas o un vendedor asociado): " + e.getMessage());
        }
    }

    @Override
    public Persona buscar(int id) {
        String sql = "SELECT * FROM persona WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("contraseña"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar persona: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Persona> obtenerPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Persona p = new Persona(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("identificacion"),
                    rs.getString("correo"),
                    rs.getString("contraseña"),
                    rs.getString("telefono")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar personas: " + e.getMessage());
        }
        return lista;
    }
}