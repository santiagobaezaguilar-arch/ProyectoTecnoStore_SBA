package dao.impl;

import config.ConexionDB;
import model.DetalleVenta;
import model.Venta;
import java.sql.*;
import java.util.List;

public class VentaDaoImpl {

    public boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        String sqlVenta = "INSERT INTO venta (id_persona, id_vendedor, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE celular SET stock = stock - ? WHERE id = ?";
        String sqlCheckStock = "SELECT modelo, stock FROM celular WHERE id = ?";

        Connection con = null;
        try {
            con = ConexionDB.getConexion();
            con.setAutoCommit(false); // Iniciar transacción por seguridad

            // 1. Insertar Venta
            int idVentaGenerado = 0;
            try (PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdPersona());
                if (venta.getIdVendedor() != null) {
                    psVenta.setInt(2, venta.getIdVendedor());
                } else {
                    psVenta.setNull(2, java.sql.Types.INTEGER);
                }
                psVenta.setDouble(3, venta.getTotal());
                psVenta.executeUpdate();

                try (ResultSet rsKeys = psVenta.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idVentaGenerado = rsKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la venta generada.");
                    }
                }
            }

            // 2. Insertar Detalles y actualizar stock
            for (DetalleVenta dv : detalles) {
                // Insertar detalle
                try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                    psDetalle.setInt(1, idVentaGenerado);
                    psDetalle.setInt(2, dv.getIdCelular());
                    psDetalle.setInt(3, dv.getCantidad());
                    psDetalle.setDouble(4, dv.getSubtotal());
                    psDetalle.executeUpdate();
                }

                // Reducir stock
                try (PreparedStatement psStock = con.prepareStatement(sqlUpdateStock)) {
                    psStock.setInt(1, dv.getCantidad());
                    psStock.setInt(2, dv.getIdCelular());
                    psStock.executeUpdate();
                }

                // Verificar stock bajo (<= 5)
                try (PreparedStatement psCheck = con.prepareStatement(sqlCheckStock)) {
                    psCheck.setInt(1, dv.getIdCelular());
                    try (ResultSet rsCheck = psCheck.executeQuery()) {
                        if (rsCheck.next()) {
                            String modeloCel = rsCheck.getString("modelo");
                            int stockRestante = rsCheck.getInt("stock");
                            if (stockRestante <= 5) {
                                System.out.println("\n⚠️ [ALERTA DE INVENTARIO] El celular '" + modeloCel + "' tiene un stock bajo (Quedan: " + stockRestante + " unidades).");
                            }
                        }
                    }
                }
            }

            con.commit(); // Confirmar cambios en BD
            System.out.println("\n¡Venta registrada con éxito en la base de datos!");
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir en caso de error
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.out.println("Error al procesar la venta: " + e.getMessage());
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }
}
