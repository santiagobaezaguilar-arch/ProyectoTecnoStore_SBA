package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VentaRegistro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String cliente;
    private String vendedor;
    private double total;
    private LocalDateTime fecha;

    public VentaRegistro(int id, String cliente, String vendedor, double total, LocalDateTime fecha) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCliente() { return cliente; }
    public String getVendedor() { return vendedor; }
    public double getTotal() { return total; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() {
        return String.format("ID Venta: %d | Cliente: %s | Vendedor: %s | Total: $%.2f | Fecha: %s", 
                id, cliente, vendedor, total, fecha);
    }
}
