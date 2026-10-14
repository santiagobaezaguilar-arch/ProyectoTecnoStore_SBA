package dao;

import model.DetalleVenta;
import model.Venta;
import java.util.List;

public interface VentaDao {
    boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles);
    List<Venta> obtenerTodasLasVentas();
}