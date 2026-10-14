package model;

import java.io.Serializable;

public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int idPersona;
    private String rol;

    public Vendedor() {
    }

    public Vendedor(int id, int idPersona, String rol) {
        this.id = id;
        this.idPersona = idPersona;
        this.rol = rol;
    }

    public Vendedor(int idPersona, String rol) {
        this.idPersona = idPersona;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "ID Vendedor: " + id + " | ID Persona: " + idPersona + " | Rol: " + rol;
    }
}
