package model;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private int idPersona; // Cliente
    private Integer idVendedor; // Vendedor (puede ser null o int)
    private LocalDateTime fecha;
    private double total;

    public Venta() {}

    public Venta(int idPersona, Integer idVendedor, double total) {
        this.idPersona = idPersona;
        this.idVendedor = idVendedor;
        this.total = total;
    }

    public Venta(int id, int idPersona, Integer idVendedor, LocalDateTime fecha, double total) {
        this.id = id;
        this.idPersona = idPersona;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdPersona() { return idPersona; }
    public void setIdPersona(int idPersona) { this.idPersona = idPersona; }

    public Integer getIdVendedor() { return idVendedor; }
    public void setIdVendedor(Integer idVendedor) { this.idVendedor = idVendedor; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
