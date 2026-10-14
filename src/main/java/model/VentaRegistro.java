package model;

import java.io.Serializable;
import java.time.LocalDateTime;

// Aca arrancamos la clase VentaRegistro, el modelo encargado de estructurar cada factura o venta que se guarda de forma persistente en el archivo binario .dat, bien pro pa q amarre :V xd
public class VentaRegistro implements Serializable {
    // Aca definimos el serialVersionUID estandar pa controlar la version del objeto cuando se serializa y deserializa en archivos binarios, gg ez
    private static final long serialVersionUID = 1L;
    
    // Aca declaramos los atributos privados de la venta: ID, nombre del cliente, nombre del vendedor, total pagado y la fecha exacta, bien organizado
    private int id;
    private String cliente;
    private String vendedor;
    private double total;
    private LocalDateTime fecha;

    // Aca armamos el constructor de la clase pa inicializar todos los datos de la venta de un solo golpe cuando se registra la compra, piolin
    public VentaRegistro(int id, String cliente, String vendedor, double total, LocalDateTime fecha) {
        // Aqui inserto esto y hago aquello para ello: asignamos los valores recibidos a los atributos internos del objeto de venta, eso tilin :V
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.fecha = fecha;
    }

    // Aca ponemos el metodo getId pa consultar el identificador unico de la venta, bien limpio xd
    public int getId() { return id; }
    // Aca ponemos el metodo setId pa modificar el ID de la venta si hace falta, wazaa
    public void setId(int id) { this.id = id; }
    // Aca declaramos getCliente pa saber el nombre del cliente que compro el telefono, bien aja
    public String getCliente() { return cliente; }
    // Aca ponemos getVendedor pa saber que cajero o empleado hizo la transaccion, chiclebomba
    public String getVendedor() { return vendedor; }
    // Aca ponemos getTotal pa consultar el precio total con IVA incluido de la venta, piolin pa q amarre
    public double getTotal() { return total; }
    // Aca ponemos getFecha pa consultar la fecha y hora exacta en que se facturo, gg ez :V
    public LocalDateTime getFecha() { return fecha; }

    // Aca sobreescribimos el metodo toString pa darle forma bonita al registro de venta cuando se imprima en la consola usando String.format, bien pro xd
    @Override
    public String toString() {
        return String.format("ID Venta: %d | Cliente: %s | Vendedor: %s | Total: $%.2f | Fecha: %s", 
                id, cliente, vendedor, total, fecha);
    }
}