package model;

import java.io.Serializable;
import java.time.LocalDateTime;

// Aca arrancamos la clase AccionLog, el modelo encargado de estructurar cada registro de auditoria que se guarda en el archivo binario .dat, bien pro pa q amarre :V xd
public class AccionLog implements Serializable {
    // Aca definimos el serialVersionUID estandar pa controlar la version del objeto cuando se serializa y deserializa en archivos binarios, gg ez
    private static final long serialVersionUID = 1L;

    // Aca declaramos los atributos privados de la auditoria: ID, usuario, tipo de accion, descripcion del evento y la fecha con hora exacta, bien organizado
    private int id;
    private String usuario;
    private String tipoAccion;
    private String descripcion;
    private LocalDateTime fechaHora;

    // Aca armamos el constructor de la clase pa inicializar todos los datos del log de un solo golpe cuando ocurre un evento, piolin
    public AccionLog(int id, String usuario, String tipoAccion, String descripcion, LocalDateTime fechaHora) {
        // Aqui inserto esto y hago aquello para ello: asignamos los valores recibidos a los atributos internos del objeto, eso tilin
        this.id = id;
        this.usuario = usuario;
        this.tipoAccion = tipoAccion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    // Aca ponemos el metodo getId pa consultar el identificador unico del log, bien limpio xd
    public int getId() { return id; }
    // Aca ponemos el metodo setId pa modificar el ID si hace falta, wazaa
    public void setId(int id) { this.id = id; }
    // Aca declaramos el metodo getUsuario pa saber que usuario ejecuto la accion, bien aja
    public String getUsuario() { return usuario; }
    // Aca ponemos getTipoAccion pa obtener la categoria del evento registrado (ej: LOGIN, VENTA, REGISTRO), chiclebomba
    public String getTipoAccion() { return tipoAccion; }
    // Aca ponemos getDescripcion pa leer el detalle completo de lo que paso en el sistema, piolin pa q amarre
    public String getDescripcion() { return descripcion; }
    // Aca ponemos getFechaHora pa consultar el timestamp exacto de cuando sucedio el evento, gg ez :V
    public LocalDateTime getFechaHora() { return fechaHora; }

    // Aca sobreescribimos el metodo toString pa darle forma bonita al log cuando se imprima en la consola usando String.format, bien pro xd
    @Override
    public String toString() {
        return String.format("ID Log: %d | Fecha: %s | Usuario: %s | Acción: %s | Detalle: %s", 
                id, fechaHora, usuario, tipoAccion, descripcion);
    }
}