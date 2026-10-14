package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AccionLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String usuario;
    private String tipoAccion;
    private String descripcion;
    private LocalDateTime fechaHora;

    public AccionLog(int id, String usuario, String tipoAccion, String descripcion, LocalDateTime fechaHora) {
        this.id = id;
        this.usuario = usuario;
        this.tipoAccion = tipoAccion;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public String getTipoAccion() { return tipoAccion; }
    public String getDescripcion() { return descripcion; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return String.format("ID Log: %d | Fecha: %s | Usuario: %s | Acción: %s | Detalle: %s", 
                id, fechaHora, usuario, tipoAccion, descripcion);
    }
}
