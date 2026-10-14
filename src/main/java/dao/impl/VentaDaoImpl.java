package dao.impl;

import config.ConexionDB;
import dao.VentaDao;
import model.DetalleVenta;
import model.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Aca arrancamos la clase VentaDaoImpl, la encargada de manejar todas las transacciones pesadas, facturas y stock en MySQL, bien pro pa q amarre :V xd
public class VentaDaoImpl implements VentaDao {

    // Aca sobreescribimos el metodo registrarVentaCompleta pa manejar transacciones ACID puras (o se guarda todo o no se guarda nada), gg ez
    @Override
    public boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles) {
        // Aca preparamos las sentencias SQL pa la cabecera de la venta, los detalles, la rebaja de stock y la alerta de inventario, wazaa
        String sqlVenta = "INSERT INTO venta (id_persona, id_vendedor, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE celular SET stock = stock - ? WHERE id = ?";
        String sqlCheckStock = "SELECT modelo, stock FROM celular WHERE id = ?";

        // Aca declaramos la conexion fuera del try pa poder controlarla con el rollback si algo llega a fallar, eso tilin
        Connection con = null;
        try {
            // Aca abrimos la conexion a la base de datos, bien seguro
            con = ConexionDB.getConexion();
            // Desactivamos el auto-commit pa iniciar la transaccion manual, chiclebomba pa q amarre
            con.setAutoCommit(false);

            int idVentaGenerado = 0;
            // Aca insertamos la venta principal y pedimos que nos devuelva el ID autoincrementable generado con RETURN_GENERATED_KEYS, gg ez
            try (PreparedStatement psVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, venta.getIdPersona());
                // Si el ID del vendedor no es nulo, lo ponemos; si no, metemos un NULL de SQL pa que no explote, bien aja
                if (venta.getIdVendedor() != null) {
                    psVenta.setInt(2, venta.getIdVendedor());
                } else {
                    psVenta.setNull(2, java.sql.Types.INTEGER);
                }
                psVenta.setDouble(3, venta.getTotal());
                psVenta.executeUpdate();

                // Aca atrapamos las llaves generadas pa sacar el ID de la venta recien creada, piolin
                try (ResultSet rsKeys = psVenta.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idVentaGenerado = rsKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID de la venta generada.");
                    }
                }
            }

            // Aca recorremos cada producto del carrito usando un ciclo for-each, bien organizado xd
            for (DetalleVenta dv : detalles) {
                // 1. Insertamos el detalle de la venta asociado al ID que acabamos de generar, wazaa
                try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                    psDetalle.setInt(1, idVentaGenerado);
                    psDetalle.setInt(2, dv.getIdCelular());
                    psDetalle.setInt(3, dv.getCantidad());
                    psDetalle.setDouble(4, dv.getSubtotal());
                    psDetalle.executeUpdate();
                }

                // 2. Aqui inserto esto y hago aquello para ello: actualizamos el inventario restando las unidades compradas al stock del celular, eso tilin
                try (PreparedStatement psStock = con.prepareStatement(sqlUpdateStock)) {
                    psStock.setInt(1, dv.getCantidad());
                    psStock.setInt(2, dv.getIdCelular());
                    psStock.executeUpdate();
                }

                // 3. Consultamos como quedo el stock actual pa lanzar una alerta si el telefono se esta quedando sin unidades, gg ez
                try (PreparedStatement psCheck = con.prepareStatement(sqlCheckStock)) {
                    psCheck.setInt(1, dv.getIdCelular());
                    try (ResultSet rsCheck = psCheck.executeQuery()) {
                        if (rsCheck.next()) {
                            String modeloCel = rsCheck.getString("modelo");
                            int stockRestante = rsCheck.getInt("stock");
                            // Si el stock remanente es menor o igual a 5, aqui inserto esto y hago aquello para ello: imprimimos la advertencia de stock bajo, chiclebomba
                            if (stockRestante <= 5) {
                                System.out.println("\n[ALERTA DE INVENTARIO] El celular '" + modeloCel + "' tiene un stock bajo (Quedan: " + stockRestante + " unidades).");
                            }
                        }
                    }
                }
            }

            // Si todo salio melo y sin errores, aplicamos el commit pa confirmar los cambios en la base de datos, bien pro pa q amarre
            con.commit();
            System.out.println("\n¡Venta registrada con exito en la base de datos!");
            return true;

        } catch (SQLException e) {
            // Si ocurre algun error o cancer durante la transaccion, aqui inserto esto y hago aquello para ello: ejecutamos un rollback pa echar patras todo y no cagar la BD, xd
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
            System.out.println("Error al procesar la venta: " + e.getMessage());
            return false;
        } finally {
            // En el bloque finally aseguramos restaurar el auto-commit y cerrar la conexion pa evitar fugas de memoria, eso tilin :V
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexion: " + e.getMessage());
                }
            }
        }
    }

    // Aca sobreescribimos el metodo obtenerTodasLasVentas pa consultar el historial completo de transacciones en la BD, bien limpio xd
    @Override
    public List<Venta> obtenerTodasLasVentas() {
        // Inicializamos la lista donde guardaremos todas las ventas encontradas, gg ez
        List<Venta> lista = new ArrayList<>();
        // Armamos la consulta SELECT * pa traernos toda la tabla de ventas, wazaa
        String sql = "SELECT * FROM venta";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // Recorremos cada registro devuelto por la base de datos con un while, bien organizado
            while (rs.next()) {
                int id = rs.getInt("id");
                int idCliente = rs.getInt("id_persona");
                Integer idVendedor = rs.getObject("id_vendedor") != null ? rs.getInt("id_vendedor") : null;
                double total = rs.getDouble("total");
                java.time.LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

                // Orden correcto de los parametros: id, idPersona, idVendedor, fecha, total, piolin pa q amarre
                Venta v = new Venta(id, idCliente, idVendedor, fecha, total);
                // Añadimos la venta a la lista, eso tilin :V
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el historial de ventas: " + e.getMessage());
        }
        // Retornamos la lista completa con todas las ventas historicas listica pa los reportes, chiclebomba xd
        return lista;
    }
}